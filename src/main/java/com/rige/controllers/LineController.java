package com.rige.controllers;

import com.rige.dto.LineDto;
import com.rige.services.ILineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lines")
@AllArgsConstructor
public class LineController {
    private final ILineService iLineService;

    @GetMapping
    public ResponseEntity<List<LineDto>> findAll() {
        return ResponseEntity.ok(iLineService.findAllActive());
    }
}