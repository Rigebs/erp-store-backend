package com.rige.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.rige.dto.response.ImageResponse;
import com.rige.entities.ImageEntity;
import com.rige.mappers.ImageMapper;
import com.rige.repositories.IImageRepository;
import com.rige.services.ICloudinaryService;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements ICloudinaryService {

    private final IImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(
            IImageRepository imageRepository, ImageMapper imageMapper, @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true));
    }

    @Override
    public ImageResponse uploadImage(MultipartFile file, String folder) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("folder", folder);
            val uploadResult = cloudinary.uploader().upload(file.getBytes(), param);
            ImageEntity imageEntity = ImageEntity.builder()
                    .publicId((String) uploadResult.get("public_id"))
                    .secureUrl((String) uploadResult.get("secure_url"))
                    .createdAt(new Date())
                    .folder(folder)
                    .status(true)
                    .flag(true)
                    .build();
            return imageMapper.toResponse(imageRepository.save(imageEntity));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}