package com.rige.controllers;

import com.rige.dto.response.ApiResponse;
import com.rige.services.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/users/images")
@RequiredArgsConstructor
public class ImageController {

    private final ICloudinaryService cloudinaryService;

    @PostMapping("/upload/{folder}")
    public ResponseEntity<ApiResponse<Map<String, String>>> save(
            @RequestParam MultipartFile file,
            @PathVariable String folder) {

        String url = cloudinaryService.uploadImage(file, folder);
        var data = Map.of("imageUrl", url);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Image uploaded successfully",
                data
        ));
    }
}
