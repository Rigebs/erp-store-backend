package com.rige.controllers;

import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.ImageResponse;
import com.rige.services.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/users/images")
@RequiredArgsConstructor
public class ImageController {

    private final ICloudinaryService iCloudinaryService;

    @PostMapping("/upload/{folder}")
    public ResponseEntity<ImageResponse> save(@RequestParam MultipartFile file,
                                              @PathVariable String folder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCloudinaryService.uploadImage(file, folder));
    }
}
