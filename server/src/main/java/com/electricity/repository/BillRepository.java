package com.electricity.repository;

import com.electricity.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserIdOrderByBillDateDesc(Long userId);
    Optional<Bill> findByIdAndUserId(Long id, Long userId);
    List<Bill> findByUserIdAndBillDateBetweenOrderByBillDateAsc(Long userId, LocalDate startDate, LocalDate endDate);
    List<Bill> findByUserIdAndStatusOrderByBillDateDesc(Long userId, String status);
}
