package com.rige.controllers;

import com.rige.dto.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ExampleController {
    @PostMapping("/hello")
    public ApiResponse hello() {
        return new ApiResponse("Hello World");
    }
}
