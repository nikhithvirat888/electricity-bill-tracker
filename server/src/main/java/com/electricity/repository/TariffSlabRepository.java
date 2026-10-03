package com.electricity.repository;

import com.electricity.entity.TariffSlab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffSlabRepository extends JpaRepository<TariffSlab, Long> {
    List<TariffSlab> findAllByOrderBySlabOrderAsc();
}
