package com.rige.controllers;

import com.rige.dto.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ExampleController {

    @GetMapping("/hello")
    public ApiResponse<Void> hello() {
        return new ApiResponse<>(true, "Hello World", null);
    }
}
