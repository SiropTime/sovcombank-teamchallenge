package com.cepheus.sovcombank.post.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class PostDtoRequest {
    private String description;
    private LocalDateTime timeStamp;
    private User author;
    @Builder
    public static class User{
        private String name;
        private String email;
    }
}
