package com.electricity.controller;

import com.electricity.config.SecurityUtils;
import com.electricity.dto.BillRequest;
import com.electricity.dto.BillResponse;
import com.electricity.service.BillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;
    private final SecurityUtils securityUtils;

    public BillController(BillService billService, SecurityUtils securityUtils) {
        this.billService = billService;
        this.securityUtils = securityUtils;
    }

    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills() {
        Long userId = securityUtils.getCurrentUserId();
        List<BillResponse> bills = billService.getAllBills(userId);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBillById(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        BillResponse bill = billService.getBillById(id, userId);
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public ResponseEntity<BillResponse> createBill(@Valid @RequestBody BillRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        BillResponse created = billService.createBill(userId, request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillResponse> updateBill(@PathVariable Long id, @Valid @RequestBody BillRequest request) {
        Long userId = securityUtils.getCurrentUserId();
        BillResponse updated = billService.updateBill(id, userId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBill(@PathVariable Long id) {
        Long userId = securityUtils.getCurrentUserId();
        billService.deleteBill(id, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bill deleted successfully");
        return ResponseEntity.ok(response);
    }
}
