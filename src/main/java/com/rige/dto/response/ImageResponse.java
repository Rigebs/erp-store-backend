package com.rige.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ImageResponse {
    private Long id;
    private String secureUrl;
    private Date createdAt;
}
