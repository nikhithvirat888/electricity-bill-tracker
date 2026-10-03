package com.electricity.service;

import com.electricity.dto.BillResponse;
import com.electricity.dto.DashboardSummaryDto;
import com.electricity.dto.MonthlyDataPointDto;
import com.electricity.entity.Appliance;
import com.electricity.entity.Bill;
import com.electricity.entity.MeterReading;
import com.electricity.repository.ApplianceRepository;
import com.electricity.repository.BillRepository;
import com.electricity.repository.MeterReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final BillRepository billRepository;
    private final MeterReadingRepository meterReadingRepository;
    private final ApplianceRepository applianceRepository;
    private final TariffService tariffService;
    private final BillService billService;

    public DashboardService(BillRepository billRepository,
                            MeterReadingRepository meterReadingRepository,
                            ApplianceRepository applianceRepository,
                            TariffService tariffService,
                            BillService billService) {
        this.billRepository = billRepository;
        this.meterReadingRepository = meterReadingRepository;
        this.applianceRepository = applianceRepository;
        this.tariffService = tariffService;
        this.billService = billService;
    }

    public DashboardSummaryDto getDashboardSummary(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate currentMonthStart = now.withDayOfMonth(1);
        LocalDate previousMonthStart = currentMonthStart.minusMonths(1);
        LocalDate previousMonthEnd = currentMonthStart.minusDays(1);

        List<Bill> allBills = billRepository.findByUserIdOrderByBillDateDesc(userId);
        List<MeterReading> allReadings = meterReadingRepository.findByUserIdOrderByReadingDateDesc(userId);
        List<Appliance> allAppliances = applianceRepository.findByUserId(userId);

        // Current month calculations
        double currentMonthUnits = 0.0;
        double currentEstimatedBill = 0.0;

        List<Bill> currentMonthBills = allBills.stream()
                .filter(b -> !b.getBillDate().isBefore(currentMonthStart) && !b.getBillDate().isAfter(now))
                .collect(Collectors.toList());

        if (!currentMonthBills.isEmpty()) {
            currentMonthUnits = currentMonthBills.stream().mapToDouble(Bill::getUnitsConsumed).sum();
            currentEstimatedBill = currentMonthBills.stream().mapToDouble(Bill::getBillAmount).sum();
        } else {
            // Check current month meter readings if no bill yet
            List<MeterReading> currentMonthReadings = allReadings.stream()
                    .filter(r -> !r.getReadingDate().isBefore(currentMonthStart) && !r.getReadingDate().isAfter(now))
                    .collect(Collectors.toList());

            if (!currentMonthReadings.isEmpty()) {
                currentMonthUnits = currentMonthReadings.stream().mapToDouble(MeterReading::getUnitsConsumed).sum();
                currentEstimatedBill = tariffService.calculateBill(currentMonthUnits).getTotalAmount();
            } else if (!allBills.isEmpty()) {
                // Default fallback to latest recorded bill if in same or recent window
                Bill latest = allBills.get(0);
                currentMonthUnits = latest.getUnitsConsumed();
                currentEstimatedBill = latest.getBillAmount();
            }
        }

        // Previous month calculations
        double previousMonthUnits = 0.0;
        double previousMonthBill = 0.0;

        List<Bill> prevMonthBills = allBills.stream()
                .filter(b -> !b.getBillDate().isBefore(previousMonthStart) && !b.getBillDate().isAfter(previousMonthEnd))
                .collect(Collectors.toList());

        if (!prevMonthBills.isEmpty()) {
            previousMonthUnits = prevMonthBills.stream().mapToDouble(Bill::getUnitsConsumed).sum();
            previousMonthBill = prevMonthBills.stream().mapToDouble(Bill::getBillAmount).sum();
        } else if (allBills.size() > 1 && currentMonthBills.isEmpty()) {
            Bill prev = allBills.get(1);
            previousMonthUnits = prev.getUnitsConsumed();
            previousMonthBill = prev.getBillAmount();
        }

        // Monthly charts for the last 6 months
        List<MonthlyDataPointDto> monthlyUsageChart = new ArrayList<>();
        List<MonthlyDataPointDto> monthlyBillChart = new ArrayList<>();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM yyyy");

        double totalHistoricalUnits = 0.0;
        int activeMonthsCount = 0;

        for (int i = 5; i >= 0; i--) {
            LocalDate monthDate = now.minusMonths(i);
            LocalDate mStart = monthDate.withDayOfMonth(1);
            LocalDate mEnd = mStart.plusMonths(1).minusDays(1);
            String monthLabel = mStart.format(monthFormatter);

            List<Bill> billsInMonth = allBills.stream()
                    .filter(b -> !b.getBillDate().isBefore(mStart) && !b.getBillDate().isAfter(mEnd))
                    .collect(Collectors.toList());

            double mUnits = 0.0;
            double mAmount = 0.0;

            if (!billsInMonth.isEmpty()) {
                mUnits = billsInMonth.stream().mapToDouble(Bill::getUnitsConsumed).sum();
                mAmount = billsInMonth.stream().mapToDouble(Bill::getBillAmount).sum();
            } else if (i == 0 && currentMonthUnits > 0) {
                mUnits = currentMonthUnits;
                mAmount = currentEstimatedBill;
            }

            mUnits = Math.round(mUnits * 100.0) / 100.0;
            mAmount = Math.round(mAmount * 100.0) / 100.0;

            monthlyUsageChart.add(new MonthlyDataPointDto(monthLabel, mUnits, mAmount));
            monthlyBillChart.add(new MonthlyDataPointDto(monthLabel, mUnits, mAmount));

            if (mUnits > 0) {
                totalHistoricalUnits += mUnits;
                activeMonthsCount++;
            }
        }

        double averageMonthlyUsage = activeMonthsCount > 0
                ? Math.round((totalHistoricalUnits / activeMonthsCount) * 100.0) / 100.0
                : Math.round(currentMonthUnits * 100.0) / 100.0;

        // Usage trend indicator & percentage calculation
        String usageIndicator = "SAME";
        double usageChangePercentage = 0.0;

        if (previousMonthUnits > 0) {
            double diff = currentMonthUnits - previousMonthUnits;
            usageChangePercentage = Math.round(((diff / previousMonthUnits) * 100.0) * 10.0) / 10.0;
            if (diff > 0) {
                usageIndicator = "INCREASE";
            } else if (diff < 0) {
                usageIndicator = "DECREASE";
                usageChangePercentage = Math.abs(usageChangePercentage);
            }
        } else if (currentMonthUnits > 0) {
            usageIndicator = "INCREASE";
            usageChangePercentage = 100.0;
        }

        // Appliance estimates
        double totalApplianceUnits = allAppliances.stream()
                .mapToDouble(Appliance::getMonthlyUnits)
                .sum();
        totalApplianceUnits = Math.round(totalApplianceUnits * 100.0) / 100.0;

        // Recent bills (last 5)
        List<BillResponse> recentBills = allBills.stream()
                .limit(5)
                .map(billService::mapToResponse)
                .collect(Collectors.toList());

        return new DashboardSummaryDto(
                Math.round(currentMonthUnits * 100.0) / 100.0,
                Math.round(currentEstimatedBill * 100.0) / 100.0,
                Math.round(previousMonthUnits * 100.0) / 100.0,
                Math.round(previousMonthBill * 100.0) / 100.0,
                averageMonthlyUsage,
                usageIndicator,
                usageChangePercentage,
                monthlyUsageChart,
                monthlyBillChart,
                recentBills,
                allAppliances.size(),
                totalApplianceUnits
        );
    }
}
