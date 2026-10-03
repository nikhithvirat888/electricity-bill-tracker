package com.electricity.dto;

import java.util.List;

public class DashboardSummaryDto {
    private Double currentMonthUnits;
    private Double currentEstimatedBill;
    private Double previousMonthUnits;
    private Double previousMonthBill;
    private Double averageMonthlyUsage;
    private String usageIndicator;       // "INCREASE", "DECREASE", "SAME"
    private Double usageChangePercentage;
    private List<MonthlyDataPointDto> monthlyUsageChart;
    private List<MonthlyDataPointDto> monthlyBillChart;
    private List<BillResponse> recentBills;
    private Integer totalAppliances;
    private Double totalApplianceEstimatedUnits;

    public DashboardSummaryDto() {
    }

    public DashboardSummaryDto(Double currentMonthUnits, Double currentEstimatedBill, Double previousMonthUnits, Double previousMonthBill, Double averageMonthlyUsage, String usageIndicator, Double usageChangePercentage, List<MonthlyDataPointDto> monthlyUsageChart, List<MonthlyDataPointDto> monthlyBillChart, List<BillResponse> recentBills, Integer totalAppliances, Double totalApplianceEstimatedUnits) {
        this.currentMonthUnits = currentMonthUnits;
        this.currentEstimatedBill = currentEstimatedBill;
        this.previousMonthUnits = previousMonthUnits;
        this.previousMonthBill = previousMonthBill;
        this.averageMonthlyUsage = averageMonthlyUsage;
        this.usageIndicator = usageIndicator;
        this.usageChangePercentage = usageChangePercentage;
        this.monthlyUsageChart = monthlyUsageChart;
        this.monthlyBillChart = monthlyBillChart;
        this.recentBills = recentBills;
        this.totalAppliances = totalAppliances;
        this.totalApplianceEstimatedUnits = totalApplianceEstimatedUnits;
    }

    public Double getCurrentMonthUnits() {
        return currentMonthUnits;
    }

    public void setCurrentMonthUnits(Double currentMonthUnits) {
        this.currentMonthUnits = currentMonthUnits;
    }

    public Double getCurrentEstimatedBill() {
        return currentEstimatedBill;
    }

    public void setCurrentEstimatedBill(Double currentEstimatedBill) {
        this.currentEstimatedBill = currentEstimatedBill;
    }

    public Double getPreviousMonthUnits() {
        return previousMonthUnits;
    }

    public void setPreviousMonthUnits(Double previousMonthUnits) {
        this.previousMonthUnits = previousMonthUnits;
    }

    public Double getPreviousMonthBill() {
        return previousMonthBill;
    }

    public void setPreviousMonthBill(Double previousMonthBill) {
        this.previousMonthBill = previousMonthBill;
    }

    public Double getAverageMonthlyUsage() {
        return averageMonthlyUsage;
    }

    public void setAverageMonthlyUsage(Double averageMonthlyUsage) {
        this.averageMonthlyUsage = averageMonthlyUsage;
    }

    public String getUsageIndicator() {
        return usageIndicator;
    }

    public void setUsageIndicator(String usageIndicator) {
        this.usageIndicator = usageIndicator;
    }

    public Double getUsageChangePercentage() {
        return usageChangePercentage;
    }

    public void setUsageChangePercentage(Double usageChangePercentage) {
        this.usageChangePercentage = usageChangePercentage;
    }

    public List<MonthlyDataPointDto> getMonthlyUsageChart() {
        return monthlyUsageChart;
    }

    public void setMonthlyUsageChart(List<MonthlyDataPointDto> monthlyUsageChart) {
        this.monthlyUsageChart = monthlyUsageChart;
    }

    public List<MonthlyDataPointDto> getMonthlyBillChart() {
        return monthlyBillChart;
    }

    public void setMonthlyBillChart(List<MonthlyDataPointDto> monthlyBillChart) {
        this.monthlyBillChart = monthlyBillChart;
    }

    public List<BillResponse> getRecentBills() {
        return recentBills;
    }

    public void setRecentBills(List<BillResponse> recentBills) {
        this.recentBills = recentBills;
    }

    public Integer getTotalAppliances() {
        return totalAppliances;
    }

    public void setTotalAppliances(Integer totalAppliances) {
        this.totalAppliances = totalAppliances;
    }

    public Double getTotalApplianceEstimatedUnits() {
        return totalApplianceEstimatedUnits;
    }

    public void setTotalApplianceEstimatedUnits(Double totalApplianceEstimatedUnits) {
        this.totalApplianceEstimatedUnits = totalApplianceEstimatedUnits;
    }
}
