package com.electricity.controller;

import com.electricity.config.SecurityUtils;
import com.electricity.dto.DashboardSummaryDto;
import com.electricity.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final SecurityUtils securityUtils;

    public DashboardController(DashboardService dashboardService, SecurityUtils securityUtils) {
        this.dashboardService = dashboardService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDto> getDashboardSummary() {
        Long userId = securityUtils.getCurrentUserId();
        DashboardSummaryDto summary = dashboardService.getDashboardSummary(userId);
        return ResponseEntity.ok(summary);
    }
}
