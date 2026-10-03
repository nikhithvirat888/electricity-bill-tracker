package com.electricity.service;

import com.electricity.dto.ApplianceRequest;
import com.electricity.dto.ApplianceResponse;
import com.electricity.dto.TariffCalculationResultDto;
import com.electricity.entity.Appliance;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.ApplianceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;
    private final TariffService tariffService;

    public ApplianceService(ApplianceRepository applianceRepository, TariffService tariffService) {
        this.applianceRepository = applianceRepository;
        this.tariffService = tariffService;
    }

    public List<ApplianceResponse> getAllAppliances(Long userId) {
        return applianceRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ApplianceResponse getApplianceById(Long id, Long userId) {
        Appliance appliance = applianceRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Appliance not found with id: " + id));
        return mapToResponse(appliance);
    }

    public ApplianceResponse createAppliance(Long userId, ApplianceRequest request) {
        double monthlyUnits = calculateMonthlyUnits(
                request.getWattage(),
                request.getQuantity(),
                request.getHoursPerDay(),
                request.getDaysPerMonth()
        );

        TariffCalculationResultDto calculation = tariffService.calculateBill(monthlyUnits);
        double estimatedCost = calculation.getTotalAmount();

        Appliance appliance = new Appliance(
                userId,
                request.getApplianceName().trim(),
                request.getWattage(),
                request.getQuantity(),
                request.getHoursPerDay(),
                request.getDaysPerMonth(),
                monthlyUnits,
                estimatedCost
        );

        Appliance saved = applianceRepository.save(appliance);
        return mapToResponse(saved);
    }

    public ApplianceResponse updateAppliance(Long id, Long userId, ApplianceRequest request) {
        Appliance appliance = applianceRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Appliance not found with id: " + id));

        double monthlyUnits = calculateMonthlyUnits(
                request.getWattage(),
                request.getQuantity(),
                request.getHoursPerDay(),
                request.getDaysPerMonth()
        );

        TariffCalculationResultDto calculation = tariffService.calculateBill(monthlyUnits);
        double estimatedCost = calculation.getTotalAmount();

        appliance.setApplianceName(request.getApplianceName().trim());
        appliance.setWattage(request.getWattage());
        appliance.setQuantity(request.getQuantity());
        appliance.setHoursPerDay(request.getHoursPerDay());
        appliance.setDaysPerMonth(request.getDaysPerMonth());
        appliance.setMonthlyUnits(monthlyUnits);
        appliance.setEstimatedCost(estimatedCost);

        Appliance updated = applianceRepository.save(appliance);
        return mapToResponse(updated);
    }

    public void deleteAppliance(Long id, Long userId) {
        Appliance appliance = applianceRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Appliance not found with id: " + id));
        applianceRepository.delete(appliance);
    }

    private double calculateMonthlyUnits(double wattage, int quantity, double hoursPerDay, int daysPerMonth) {
        double totalWattHours = wattage * quantity * hoursPerDay * daysPerMonth;
        double units = totalWattHours / 1000.0;
        return Math.round(units * 100.0) / 100.0;
    }

    public ApplianceResponse mapToResponse(Appliance appliance) {
        double dailyUnits = Math.round(((appliance.getWattage() * appliance.getQuantity() * appliance.getHoursPerDay()) / 1000.0) * 100.0) / 100.0;
        return new ApplianceResponse(
                appliance.getId(),
                appliance.getUserId(),
                appliance.getApplianceName(),
                appliance.getWattage(),
                appliance.getQuantity(),
                appliance.getHoursPerDay(),
                appliance.getDaysPerMonth(),
                dailyUnits,
                appliance.getMonthlyUnits(),
                appliance.getEstimatedCost(),
                appliance.getCreatedAt()
        );
    }
}
