package com.electricity.controller;

import com.electricity.config.SecurityUtils;
import com.electricity.dto.ApplianceRequest;
import com.electricity.dto.ApplianceResponse;
import com.electricity.service.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

    private final ApplianceService applianceService;
    private final SecurityUtils securityUtils;

    public ApplianceController(ApplianceService applianceService, SecurityUtils securityUtils) {
        this.applianceService = applianceService;
        this.securityUtils = securityUtils;
    }

    @GetMapping
    public ResponseEntity<List<ApplianceResponse>> getAllAppliances() {
        Long userId = securityUtils.getCurrentUserId();
        List<ApplianceResponse> appliances = applianceService.getAllAppliances(userId);
        return ResponseEntity.ok(appliances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplianceResponse> getApplianceById(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        ApplianceResponse appliance = applianceService.getApplianceById(id, userId);
        return ResponseEntity.ok(appliance);
    }

    @PostMapping
    public ResponseEntity<ApplianceResponse> createAppliance(@Valid @RequestBody ApplianceRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        ApplianceResponse created = applianceService.createAppliance(userId, request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplianceResponse> updateAppliance(@PathVariable Long id, @Valid @RequestBody ApplianceRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        ApplianceResponse updated = applianceService.updateAppliance(id, userId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAppliance(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        applianceService.deleteAppliance(id, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Appliance deleted successfully");
        return ResponseEntity.ok(response);
    }
}
