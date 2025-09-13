package com.rige.controllers;

import com.rige.dto.request.CustomerRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.CustomerResponse;
import com.rige.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody CustomerRequest customerRequest) {
        customerService.save(customerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Customer created successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponse>>> findAll(
            @PageableDefault Pageable pageable) {
        Page<CustomerResponse> result = customerService.findAll(pageable);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customers retrieved successfully", result)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> findById(@PathVariable Long id) {
        var customer = customerService.findById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer retrieved successfully", customer)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody CustomerRequest customerRequest) {
        customerService.update(id, customerRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer updated successfully", null)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleEnabled(@PathVariable Long id) {
        customerService.toggleEnabled(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer status updated successfully", null)
        );
    }
}
