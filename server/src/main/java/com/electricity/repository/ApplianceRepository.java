package com.electricity.repository;

import com.electricity.entity.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    List<Appliance> findByUserId(Long userId);
    Optional<Appliance> findByIdAndUserId(Long id, Long userId);
}
