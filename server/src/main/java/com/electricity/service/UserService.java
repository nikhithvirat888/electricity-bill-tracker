package com.electricity.service;

import com.electricity.dto.*;
import com.electricity.entity.User;
import com.electricity.exception.BadRequestException;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("An account with this email address already exists.");
        }

        User user = new User(
                request.getName(),
                request.getEmail().toLowerCase().trim(),
                passwordEncoder.encode(request.getPassword()),
                request.getConsumerNumber().trim(),
                request.getAddress()
        );

        User savedUser = userRepository.save(user);

        String basicAuthToken = Base64.getEncoder().encodeToString(
                (request.getEmail().toLowerCase().trim() + ":" + request.getPassword()).getBytes(StandardCharsets.UTF_8)
        );

        UserDto userDto = mapToDto(savedUser);
        return new AuthResponse(basicAuthToken, userDto, "Registration successful");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase().trim())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String basicAuthToken = Base64.getEncoder().encodeToString(
                (request.getEmail().toLowerCase().trim() + ":" + request.getPassword()).getBytes(StandardCharsets.UTF_8)
        );

        UserDto userDto = mapToDto(user);
        return new AuthResponse(basicAuthToken, userDto, "Login successful");
    }

    public UserDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return mapToDto(user);
    }

    public UserDto getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email.toLowerCase().trim())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return mapToDto(user);
    }

    public UserDto updateUserProfile(Long userId, UserProfileUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setName(request.getName());
        user.setConsumerNumber(request.getConsumerNumber());
        user.setAddress(request.getAddress());

        if (request.getNewPassword() != null && !request.getNewPassword().trim().isEmpty()) {
            if (request.getNewPassword().length() < 6) {
                throw new BadRequestException("New password must be at least 6 characters long");
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    public UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getConsumerNumber(),
                user.getAddress()
        );
    }
}
