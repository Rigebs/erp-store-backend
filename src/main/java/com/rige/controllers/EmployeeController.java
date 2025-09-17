package com.rige.controllers;

import com.rige.dto.request.EmployeeRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.EmployeeResponse;
import com.rige.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody EmployeeRequest request) {
        employeeService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> findAll(@PageableDefault Pageable pageable) {
        Page<EmployeeResponse> result = employeeService.findAll(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employees retrieved successfully", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> findById(@PathVariable Long id) {
        var employee = employeeService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee retrieved successfully", employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        employeeService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee updated successfully", null));
    }
}