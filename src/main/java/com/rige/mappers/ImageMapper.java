package com.rige.mappers;

import com.rige.dto.response.ImageResponse;
import com.rige.entities.ImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponse toResponse(ImageEntity entity) {
        if (entity == null) {
            return null;
        }
        return ImageResponse.builder()
                .id(entity.getId())
                .secureUrl(entity.getSecureUrl())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
