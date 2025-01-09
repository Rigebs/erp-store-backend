package com.rige.services;


import com.rige.dto.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
    ImageResponse uploadImage(MultipartFile file, String folder);
}
