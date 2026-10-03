package com.electricity.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appliances")
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "appliance_name", nullable = false)
    private String applianceName;

    @Column(nullable = false)
    private Double wattage;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "hours_per_day", nullable = false)
    private Double hoursPerDay;

    @Column(name = "days_per_month", nullable = false)
    private Integer daysPerMonth;

    @Column(name = "monthly_units", nullable = false)
    private Double monthlyUnits;

    @Column(name = "estimated_cost", nullable = false)
    private Double estimatedCost;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Appliance() {
    }

    public Appliance(Long userId, String applianceName, Double wattage, Integer quantity, Double hoursPerDay, Integer daysPerMonth, Double monthlyUnits, Double estimatedCost) {
        this.userId = userId;
        this.applianceName = applianceName;
        this.wattage = wattage;
        this.quantity = quantity;
        this.hoursPerDay = hoursPerDay;
        this.daysPerMonth = daysPerMonth;
        this.monthlyUnits = monthlyUnits;
        this.estimatedCost = estimatedCost;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
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
