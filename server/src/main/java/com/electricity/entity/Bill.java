package com.electricity.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "consumer_number")
    private String consumerNumber;

    @Column(name = "previous_reading", nullable = false)
    private Double previousReading;

    @Column(name = "current_reading", nullable = false)
    private Double currentReading;

    @Column(name = "units_consumed", nullable = false)
    private Double unitsConsumed;

    @Column(name = "bill_amount", nullable = false)
    private Double billAmount;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(nullable = false)
    private String status; // "PAID", "UNPAID", "PENDING"

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Bill() {
    }

    public Bill(Long userId, String consumerNumber, Double previousReading, Double currentReading, Double unitsConsumed, Double billAmount, LocalDate billDate, LocalDate dueDate, String status) {
        this.userId = userId;
        this.consumerNumber = consumerNumber;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.billAmount = billAmount;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.dueDate == null && this.billDate != null) {
            this.dueDate = this.billDate.plusDays(15);
        }
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
