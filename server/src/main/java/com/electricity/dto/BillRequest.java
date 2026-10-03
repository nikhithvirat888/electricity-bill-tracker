package com.electricity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class BillRequest {

    private String consumerNumber;

    @NotNull(message = "Previous reading is required")
    @PositiveOrZero(message = "Previous reading must be positive or zero")
    private Double previousReading;

    @NotNull(message = "Current reading is required")
    @PositiveOrZero(message = "Current reading must be positive or zero")
    private Double currentReading;

    private Double unitsConsumed;

    private Double billAmount;

    @NotNull(message = "Bill date is required")
    private LocalDate billDate;

    private LocalDate dueDate;

    @NotBlank(message = "Status is required")
    private String status; // "PAID", "UNPAID", "PENDING"

    public BillRequest() {
    }

    public BillRequest(String consumerNumber, Double previousReading, Double currentReading, Double unitsConsumed, Double billAmount, LocalDate billDate, LocalDate dueDate, String status) {
        this.consumerNumber = consumerNumber;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.billAmount = billAmount;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
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
}
