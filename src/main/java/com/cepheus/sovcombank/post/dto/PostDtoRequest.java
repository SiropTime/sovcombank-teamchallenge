package com.cepheus.sovcombank.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class PostDtoRequest {
    private String description;
    private String title;
    private LocalDateTime timeStamp;
    private User author;
    @Builder
    @Data
    public static class User{
        private Long id;
        private String name;
        private String email;
    }
}
