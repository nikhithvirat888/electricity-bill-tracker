package com.electricity.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ApplianceRequest {

    @NotBlank(message = "Appliance name is required")
    private String applianceName;

    @NotNull(message = "Power in watts is required")
    @Positive(message = "Wattage must be a positive number")
    private Double wattage;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Hours used per day is required")
    @Positive(message = "Hours per day must be greater than 0")
    @Max(value = 24, message = "Hours per day cannot exceed 24")
    private Double hoursPerDay;

    @NotNull(message = "Number of days is required")
    @Min(value = 1, message = "Number of days must be at least 1")
    @Max(value = 31, message = "Number of days cannot exceed 31")
    private Integer daysPerMonth;

    public ApplianceRequest() {
    }

    public ApplianceRequest(String applianceName, Double wattage, Integer quantity, Double hoursPerDay, Integer daysPerMonth) {
        this.applianceName = applianceName;
        this.wattage = wattage;
        this.quantity = quantity;
        this.hoursPerDay = hoursPerDay;
        this.daysPerMonth = daysPerMonth;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public Double getWattage() {
        return wattage;
    }

    public void setWattage(Double wattage) {
        this.wattage = wattage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(Double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public Integer getDaysPerMonth() {
        return daysPerMonth;
    }

    public void setDaysPerMonth(Integer daysPerMonth) {
        this.daysPerMonth = daysPerMonth;
    }
}
