package com.electricity.controller;

import com.electricity.config.SecurityUtils;
import com.electricity.dto.MeterReadingRequest;
import com.electricity.dto.MeterReadingResponse;
import com.electricity.service.MeterReadingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meter-readings")
public class MeterReadingController {

    private final MeterReadingService meterReadingService;
    private final SecurityUtils securityUtils;

    public MeterReadingController(MeterReadingService meterReadingService, SecurityUtils securityUtils) {
        this.meterReadingService = meterReadingService;
        this.securityUtils = securityUtils;
    }

    @GetMapping
    public ResponseEntity<List<MeterReadingResponse>> getAllReadings() {
        Long userId = securityUtils.getCurrentUserId();
        List<MeterReadingResponse> readings = meterReadingService.getAllReadings(userId);
        return ResponseEntity.ok(readings);
    }

    @GetMapping("/latest")
    public ResponseEntity<MeterReadingResponse> getLatestReading() {
        Long userId = securityUtils.getCurrentUserId();
        MeterReadingResponse latest = meterReadingService.getLatestReading(userId);
        return ResponseEntity.ok(latest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeterReadingResponse> getReadingById(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        MeterReadingResponse reading = meterReadingService.getReadingById(id, userId);
        return ResponseEntity.ok(reading);
    }

    @PostMapping
    public ResponseEntity<MeterReadingResponse> createReading(@Valid @RequestBody MeterReadingRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        MeterReadingResponse created = meterReadingService.createReading(userId, request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeterReadingResponse> updateReading(@PathVariable Long id, @Valid @RequestBody MeterReadingRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        MeterReadingResponse updated = meterReadingService.updateReading(id, userId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReading(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        meterReadingService.deleteReading(id, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Meter reading deleted successfully");
        return ResponseEntity.ok(response);
    }
}
