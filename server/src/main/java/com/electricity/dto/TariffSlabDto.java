package com.electricity.dto;

public class TariffSlabDto {
    private Long id;
    private String slabName;
    private Double fromUnits;
    private Double toUnits;
    private Double ratePerUnit;
    private Integer slabOrder;

    public TariffSlabDto() {
    }

    public TariffSlabDto(Long id, String slabName, Double fromUnits, Double toUnits, Double ratePerUnit, Integer slabOrder) {
        this.id = id;
        this.slabName = slabName;
        this.fromUnits = fromUnits;
        this.toUnits = toUnits;
        this.ratePerUnit = ratePerUnit;
        this.slabOrder = slabOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlabName() {
        return slabName;
    }

    public void setSlabName(String slabName) {
        this.slabName = slabName;
    }

    public Double getFromUnits() {
        return fromUnits;
    }

    public void setFromUnits(Double fromUnits) {
        this.fromUnits = fromUnits;
    }

    public Double getToUnits() {
        return toUnits;
    }

    public void setToUnits(Double toUnits) {
        this.toUnits = toUnits;
    }

    public Double getRatePerUnit() {
        return ratePerUnit;
    }

    public void setRatePerUnit(Double ratePerUnit) {
        this.ratePerUnit = ratePerUnit;
    }

    public Integer getSlabOrder() {
        return slabOrder;
    }

    public void setSlabOrder(Integer slabOrder) {
        this.slabOrder = slabOrder;
    }
}
