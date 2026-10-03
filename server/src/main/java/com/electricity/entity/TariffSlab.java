package com.electricity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tariff_slabs")
public class TariffSlab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slab_name", nullable = false)
    private String slabName;

    @Column(name = "from_units", nullable = false)
    private Double fromUnits;

    @Column(name = "to_units")
    private Double toUnits; // null indicates infinite / 301+

    @Column(name = "rate_per_unit", nullable = false)
    private Double ratePerUnit;

    @Column(name = "slab_order", nullable = false)
    private Integer slabOrder;

    public TariffSlab() {
    }

    public TariffSlab(String slabName, Double fromUnits, Double toUnits, Double ratePerUnit, Integer slabOrder) {
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
