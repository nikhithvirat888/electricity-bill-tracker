package com.electricity.config;

import com.electricity.entity.User;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new ResourceNotFoundException("No authenticated user found in session");
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email.toLowerCase().trim())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
