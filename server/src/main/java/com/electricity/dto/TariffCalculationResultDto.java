package com.electricity.dto;

import java.util.List;

public class TariffCalculationResultDto {
    private Double units;
    private Double totalAmount;
    private Double fixedCharge;
    private List<TariffSlabBreakdownDto> slabBreakdowns;

    public TariffCalculationResultDto() {
    }

    public TariffCalculationResultDto(Double units, Double totalAmount, Double fixedCharge, List<TariffSlabBreakdownDto> slabBreakdowns) {
        this.units = units;
        this.totalAmount = totalAmount;
        this.fixedCharge = fixedCharge;
        this.slabBreakdowns = slabBreakdowns;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFixedCharge() {
        return fixedCharge;
    }

    public void setFixedCharge(Double fixedCharge) {
        this.fixedCharge = fixedCharge;
    }

    public List<TariffSlabBreakdownDto> getSlabBreakdowns() {
        return slabBreakdowns;
    }

    public void setSlabBreakdowns(List<TariffSlabBreakdownDto> slabBreakdowns) {
        this.slabBreakdowns = slabBreakdowns;
    }
}
