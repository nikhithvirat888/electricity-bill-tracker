package com.electricity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class MeterReadingRequest {

    @NotNull(message = "Previous reading is required")
    @PositiveOrZero(message = "Previous reading must be positive or zero")
    private Double previousReading;

    @NotNull(message = "Current reading is required")
    @PositiveOrZero(message = "Current reading must be positive or zero")
    private Double currentReading;

    @NotNull(message = "Reading date is required")
    private LocalDate readingDate;

    public MeterReadingRequest() {
    }

    public MeterReadingRequest(Double previousReading, Double currentReading, LocalDate readingDate) {
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.readingDate = readingDate;
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

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }
}
