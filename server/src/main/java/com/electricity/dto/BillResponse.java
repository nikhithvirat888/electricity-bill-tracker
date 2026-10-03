package com.electricity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillResponse {
    private Long id;
    private Long userId;
    private String consumerNumber;
    private Double previousReading;
    private Double currentReading;
    private Double unitsConsumed;
    private Double billAmount;
    private LocalDate billDate;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createdAt;

    public BillResponse() {
    }

    public BillResponse(Long id, Long userId, String consumerNumber, Double previousReading, Double currentReading, Double unitsConsumed, Double billAmount, LocalDate billDate, LocalDate dueDate, String status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.consumerNumber = consumerNumber;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.billAmount = billAmount;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public Double getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(Double previousReading) {
        this.previousReading = previousReading;
    }

    public Double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(Double currentReading) {
        this.currentReading = currentReading;
    }

    public Double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(Double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
