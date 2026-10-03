package com.electricity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MeterReadingResponse {
    private Long id;
    private Long userId;
    private Double previousReading;
    private Double currentReading;
    private Double unitsConsumed;
    private LocalDate readingDate;
    private LocalDateTime createdAt;

    public MeterReadingResponse() {
    }

    public MeterReadingResponse(Long id, Long userId, Double previousReading, Double currentReading, Double unitsConsumed, LocalDate readingDate, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = unitsConsumed;
        this.readingDate = readingDate;
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

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
