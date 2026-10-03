package com.electricity.service;

import com.electricity.dto.BillRequest;
import com.electricity.dto.BillResponse;
import com.electricity.dto.TariffCalculationResultDto;
import com.electricity.entity.Bill;
import com.electricity.entity.User;
import com.electricity.exception.BadRequestException;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.BillRepository;
import com.electricity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final TariffService tariffService;

    public BillService(BillRepository billRepository, UserRepository userRepository, TariffService tariffService) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.tariffService = tariffService;
    }

    public List<BillResponse> getAllBills(Long userId) {
        return billRepository.findByUserIdOrderByBillDateDesc(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BillResponse getBillById(Long id, Long userId) {
        Bill bill = billRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        return mapToResponse(bill);
    }

    public BillResponse createBill(Long userId, BillRequest request) {
        if (request.getCurrentReading() < request.getPreviousReading()) {
            throw new BadRequestException("Current meter reading cannot be lower than previous meter reading.");
        }

        double unitsConsumed = (request.getUnitsConsumed() != null && request.getUnitsConsumed() > 0)
                ? request.getUnitsConsumed()
                : Math.round((request.getCurrentReading() - request.getPreviousReading()) * 100.0) / 100.0;

        double billAmount;
        if (request.getBillAmount() != null && request.getBillAmount() > 0) {
            billAmount = Math.round(request.getBillAmount() * 100.0) / 100.0;
        } else {
            TariffCalculationResultDto calculation = tariffService.calculateBill(unitsConsumed);
            billAmount = calculation.getTotalAmount();
        }

        String consumerNumber = request.getConsumerNumber();
        if (consumerNumber == null || consumerNumber.trim().isEmpty()) {
            User user = userRepository.findById(userId).orElse(null);
            consumerNumber = (user != null) ? user.getConsumerNumber() : "N/A";
        }

        LocalDate dueDate = (request.getDueDate() != null)
                ? request.getDueDate()
                : request.getBillDate().plusDays(15);

        String status = (request.getStatus() != null && !request.getStatus().trim().isEmpty())
                ? request.getStatus().toUpperCase()
                : "PENDING";

        Bill bill = new Bill(
                userId,
                consumerNumber,
                request.getPreviousReading(),
                request.getCurrentReading(),
                unitsConsumed,
                billAmount,
                request.getBillDate(),
                dueDate,
                status
        );

        Bill saved = billRepository.save(bill);
        return mapToResponse(saved);
    }

    public BillResponse updateBill(Long id, Long userId, BillRequest request) {
        Bill bill = billRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));

        if (request.getCurrentReading() < request.getPreviousReading()) {
            throw new BadRequestException("Current meter reading cannot be lower than previous meter reading.");
        }

        double unitsConsumed = (request.getUnitsConsumed() != null && request.getUnitsConsumed() > 0)
                ? request.getUnitsConsumed()
                : Math.round((request.getCurrentReading() - request.getPreviousReading()) * 100.0) / 100.0;

        double billAmount;
        if (request.getBillAmount() != null && request.getBillAmount() > 0) {
            billAmount = Math.round(request.getBillAmount() * 100.0) / 100.0;
        } else {
            TariffCalculationResultDto calculation = tariffService.calculateBill(unitsConsumed);
            billAmount = calculation.getTotalAmount();
        }

        if (request.getConsumerNumber() != null && !request.getConsumerNumber().trim().isEmpty()) {
            bill.setConsumerNumber(request.getConsumerNumber());
        }

        bill.setPreviousReading(request.getPreviousReading());
        bill.setCurrentReading(request.getCurrentReading());
        bill.setUnitsConsumed(unitsConsumed);
        bill.setBillAmount(billAmount);
        bill.setBillDate(request.getBillDate());
        if (request.getDueDate() != null) {
            bill.setDueDate(request.getDueDate());
        }
        if (request.getStatus() != null) {
            bill.setStatus(request.getStatus().toUpperCase());
        }

        Bill updated = billRepository.save(bill);
        return mapToResponse(updated);
    }

    public void deleteBill(Long id, Long userId) {
        Bill bill = billRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
        billRepository.delete(bill);
    }

    public BillResponse mapToResponse(Bill bill) {
        return new BillResponse(
                bill.getId(),
                bill.getUserId(),
                bill.getConsumerNumber(),
                bill.getPreviousReading(),
                bill.getCurrentReading(),
                bill.getUnitsConsumed(),
                bill.getBillAmount(),
                bill.getBillDate(),
                bill.getDueDate(),
                bill.getStatus(),
                bill.getCreatedAt()
        );
    }
}
