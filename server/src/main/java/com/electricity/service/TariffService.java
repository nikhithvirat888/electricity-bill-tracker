package com.electricity.service;

import com.electricity.dto.TariffCalculationResultDto;
import com.electricity.dto.TariffSlabBreakdownDto;
import com.electricity.dto.TariffSlabDto;
import com.electricity.entity.TariffSlab;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.TariffSlabRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TariffService {

    private final TariffSlabRepository tariffSlabRepository;
    private static final double FIXED_CHARGE = 50.0;

    public TariffService(TariffSlabRepository tariffSlabRepository) {
        this.tariffSlabRepository = tariffSlabRepository;
    }

    @PostConstruct
    public void initDefaultTariffSlabs() {
        if (tariffSlabRepository.count() == 0) {
            List<TariffSlab> defaultSlabs = List.of(
                    new TariffSlab("0 - 100 units", 0.0, 100.0, 3.00, 1),
                    new TariffSlab("101 - 200 units", 100.0, 200.0, 4.50, 2),
                    new TariffSlab("201 - 300 units", 200.0, 300.0, 6.00, 3),
                    new TariffSlab("301+ units", 300.0, null, 8.00, 4)
            );
            tariffSlabRepository.saveAll(defaultSlabs);
        }
    }

    public List<TariffSlabDto> getAllTariffSlabs() {
        return tariffSlabRepository.findAllByOrderBySlabOrderAsc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TariffSlabDto updateTariffSlab(Long id, TariffSlabDto dto) {
        TariffSlab slab = tariffSlabRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff slab not found with id: " + id));

        slab.setSlabName(dto.getSlabName());
        slab.setFromUnits(dto.getFromUnits());
        slab.setToUnits(dto.getToUnits());
        slab.setRatePerUnit(dto.getRatePerUnit());
        if (dto.getSlabOrder() != null) {
            slab.setSlabOrder(dto.getSlabOrder());
        }

        TariffSlab saved = tariffSlabRepository.save(slab);
        return mapToDto(saved);
    }

    public TariffCalculationResultDto calculateBill(double units) {
        if (units <= 0) {
            return new TariffCalculationResultDto(0.0, 0.0, 0.0, new ArrayList<>());
        }

        List<TariffSlab> slabs = tariffSlabRepository.findAllByOrderBySlabOrderAsc();
        if (slabs.isEmpty()) {
            initDefaultTariffSlabs();
            slabs = tariffSlabRepository.findAllByOrderBySlabOrderAsc();
        }

        double totalSlabCost = 0.0;
        List<TariffSlabBreakdownDto> breakdowns = new ArrayList<>();

        for (TariffSlab slab : slabs) {
            double from = slab.getFromUnits();
            Double to = slab.getToUnits();

            if (units > from) {
                double slabCapacity = (to != null) ? (to - from) : Double.MAX_VALUE;
                double unitsInThisSlab = Math.min(units - from, slabCapacity);

                if (unitsInThisSlab > 0) {
                    double slabCost = unitsInThisSlab * slab.getRatePerUnit();
                    totalSlabCost += slabCost;
                    breakdowns.add(new TariffSlabBreakdownDto(
                            slab.getSlabName(),
                            Math.round(unitsInThisSlab * 100.0) / 100.0,
                            slab.getRatePerUnit(),
                            Math.round(slabCost * 100.0) / 100.0
                    ));
                }
            }
        }

        double totalAmount = totalSlabCost + FIXED_CHARGE;
        return new TariffCalculationResultDto(
                Math.round(units * 100.0) / 100.0,
                Math.round(totalAmount * 100.0) / 100.0,
                FIXED_CHARGE,
                breakdowns
        );
    }

    private TariffSlabDto mapToDto(TariffSlab slab) {
        return new TariffSlabDto(
                slab.getId(),
                slab.getSlabName(),
                slab.getFromUnits(),
                slab.getToUnits(),
                slab.getRatePerUnit(),
                slab.getSlabOrder()
        );
    }
}
