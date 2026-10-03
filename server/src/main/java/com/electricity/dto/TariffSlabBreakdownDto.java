package com.electricity.dto;

public class TariffSlabBreakdownDto {
    private String slabName;
    private Double unitsInSlab;
    private Double ratePerUnit;
    private Double slabCost;

    public TariffSlabBreakdownDto() {
    }

    public TariffSlabBreakdownDto(String slabName, Double unitsInSlab, Double ratePerUnit, Double slabCost) {
        this.slabName = slabName;
        this.unitsInSlab = unitsInSlab;
        this.ratePerUnit = ratePerUnit;
        this.slabCost = slabCost;
    }

    public String getSlabName() {
        return slabName;
    }

    public void setSlabName(String slabName) {
        this.slabName = slabName;
    }

    public Double getUnitsInSlab() {
        return unitsInSlab;
    }

    public void setUnitsInSlab(Double unitsInSlab) {
        this.unitsInSlab = unitsInSlab;
    }

    public Double getRatePerUnit() {
        return ratePerUnit;
    }

    public void setRatePerUnit(Double ratePerUnit) {
        this.ratePerUnit = ratePerUnit;
    }

    public Double getSlabCost() {
        return slabCost;
    }

    public void setSlabCost(Double slabCost) {
        this.slabCost = slabCost;
    }
}
