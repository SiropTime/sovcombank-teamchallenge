package com.cepheus.sovcombank.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class PostDto {
    private String description;
    private String title;
    private String image;
    private LocalDateTime timeStamp;
    private String userEmail;
}
