package com.electricity.dto;

public class MonthlyDataPointDto {
    private String month;       // e.g. "Jan", "Feb 2026"
    private Double units;       // Units consumed
    private Double billAmount;  // Bill amount

    public MonthlyDataPointDto() {
    }

    public MonthlyDataPointDto(String month, Double units, Double billAmount) {
        this.month = month;
        this.units = units;
        this.billAmount = billAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }
}
