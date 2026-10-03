package com.electricity.dto;

import java.time.LocalDateTime;

public class ApplianceResponse {
    private Long id;
    private Long userId;
    private String applianceName;
    private Double wattage;
    private Integer quantity;
    private Double hoursPerDay;
    private Integer daysPerMonth;
    private Double dailyUnits;
    private Double monthlyUnits;
    private Double estimatedCost;
    private LocalDateTime createdAt;

    public ApplianceResponse() {
    }

    public ApplianceResponse(Long id, Long userId, String applianceName, Double wattage, Integer quantity, Double hoursPerDay, Integer daysPerMonth, Double dailyUnits, Double monthlyUnits, Double estimatedCost, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.applianceName = applianceName;
        this.wattage = wattage;
        this.quantity = quantity;
        this.hoursPerDay = hoursPerDay;
        this.daysPerMonth = daysPerMonth;
        this.dailyUnits = dailyUnits;
        this.monthlyUnits = monthlyUnits;
        this.estimatedCost = estimatedCost;
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

    public Double getDailyUnits() {
        return dailyUnits;
    }

    public void setDailyUnits(Double dailyUnits) {
        this.dailyUnits = dailyUnits;
    }

    public Double getMonthlyUnits() {
        return monthlyUnits;
    }

    public void setMonthlyUnits(Double monthlyUnits) {
        this.monthlyUnits = monthlyUnits;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
