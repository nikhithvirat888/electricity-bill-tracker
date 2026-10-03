package com.electricity.controller;

import com.electricity.dto.TariffCalculationResultDto;
import com.electricity.dto.TariffSlabDto;
import com.electricity.service.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
public class TariffController {

    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    public ResponseEntity<List<TariffSlabDto>> getAllTariffSlabs() {
        return ResponseEntity.ok(tariffService.getAllTariffSlabs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TariffSlabDto> updateTariffSlab(@PathVariable Long id, @RequestBody TariffSlabDto dto) {
        return ResponseEntity.ok(tariffService.updateTariffSlab(id, dto));
    }

    @PostMapping("/calculate")
    public ResponseEntity<TariffCalculationResultDto> calculateTariff(@RequestParam(defaultValue = "0") double units) {
        return ResponseEntity.ok(tariffService.calculateBill(units));
    }
}
