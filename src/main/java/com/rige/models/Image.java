package com.rige.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Image {
    private Long id;
    private String secureUrl;
    private Date createdAt;
}
