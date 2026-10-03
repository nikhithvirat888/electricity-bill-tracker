package com.electricity.controller;

import com.electricity.config.SecurityUtils;
import com.electricity.dto.UserDto;
import com.electricity.dto.UserProfileUpdateRequest;
import com.electricity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final SecurityUtils securityUtils;

    public UserController(UserService userService, SecurityUtils securityUtils) {
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        Long userId = securityUtils.getCurrentUserId();
        UserDto profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateProfile(@Valid @RequestBody UserProfileUpdateRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        UserDto updated = userService.updateUserProfile(userId, request);
        return ResponseEntity.ok(updated);
    }
}
