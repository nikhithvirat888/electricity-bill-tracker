package com.electricity.service;

import com.electricity.dto.MeterReadingRequest;
import com.electricity.dto.MeterReadingResponse;
import com.electricity.entity.MeterReading;
import com.electricity.exception.BadRequestException;
import com.electricity.exception.ResourceNotFoundException;
import com.electricity.repository.MeterReadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterReadingService {

    private final MeterReadingRepository meterReadingRepository;

    public MeterReadingService(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public List<MeterReadingResponse> getAllReadings(Long userId) {
        return meterReadingRepository.findByUserIdOrderByReadingDateDesc(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public MeterReadingResponse getLatestReading(Long userId) {
        return meterReadingRepository.findTopByUserIdOrderByReadingDateDesc(userId)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public MeterReadingResponse getReadingById(Long id, Long userId) {
        MeterReading reading = meterReadingRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Meter reading not found with id: " + id));
        return mapToResponse(reading);
    }

    public MeterReadingResponse createReading(Long userId, MeterReadingRequest request) {
        if (request.getCurrentReading() < request.getPreviousReading()) {
            throw new BadRequestException("Current meter reading cannot be lower than previous meter reading.");
        }

        double unitsConsumed = Math.round((request.getCurrentReading() - request.getPreviousReading()) * 100.0) / 100.0;

        MeterReading reading = new MeterReading(
                userId,
                request.getPreviousReading(),
                request.getCurrentReading(),
                unitsConsumed,
                request.getReadingDate()
        );

        MeterReading saved = meterReadingRepository.save(reading);
        return mapToResponse(saved);
    }

    public MeterReadingResponse updateReading(Long id, Long userId, MeterReadingRequest request) {
        MeterReading reading = meterReadingRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Meter reading not found with id: " + id));

        if (request.getCurrentReading() < request.getPreviousReading()) {
            throw new BadRequestException("Current meter reading cannot be lower than previous meter reading.");
        }

        double unitsConsumed = Math.round((request.getCurrentReading() - request.getPreviousReading()) * 100.0) / 100.0;

        reading.setPreviousReading(request.getPreviousReading());
        reading.setCurrentReading(request.getCurrentReading());
        reading.setUnitsConsumed(unitsConsumed);
        reading.setReadingDate(request.getReadingDate());

        MeterReading updated = meterReadingRepository.save(reading);
        return mapToResponse(updated);
    }

    public void deleteReading(Long id, Long userId) {
        MeterReading reading = meterReadingRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Meter reading not found with id: " + id));
        meterReadingRepository.delete(reading);
    }

    public MeterReadingResponse mapToResponse(MeterReading reading) {
        return new MeterReadingResponse(
                reading.getId(),
                reading.getUserId(),
                reading.getPreviousReading(),
                reading.getCurrentReading(),
                reading.getUnitsConsumed(),
                reading.getReadingDate(),
                reading.getCreatedAt()
        );
    }
}
