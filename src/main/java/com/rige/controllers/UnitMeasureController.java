package com.rige.controllers;

import com.rige.dto.UnitMeasureDto;
import com.rige.services.IUnitMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/units-measure")
@AllArgsConstructor
public class UnitMeasureController {
    private final IUnitMeasureService iUnitMeasureService;

    @GetMapping
    public ResponseEntity<List<UnitMeasureDto>> findAll() {
        return ResponseEntity.ok(iUnitMeasureService.findAllActive());
    }
}