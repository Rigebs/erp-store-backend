package com.rige.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ImageDto {
    private Long id;
    private String secureUrl;
    private Date createdAt;
}
