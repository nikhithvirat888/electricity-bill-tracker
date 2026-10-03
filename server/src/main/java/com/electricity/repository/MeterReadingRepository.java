package com.electricity.repository;

import com.electricity.entity.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {
    List<MeterReading> findByUserIdOrderByReadingDateDesc(Long userId);
    Optional<MeterReading> findTopByUserIdOrderByReadingDateDesc(Long userId);
    Optional<MeterReading> findByIdAndUserId(Long id, Long userId);
}
