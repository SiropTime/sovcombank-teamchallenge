package com.cepheus.sovcombank.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class UserFoAdminDto {
    private String name;

    private String email;

    private String password;

    private Boolean banned;

    private Boolean approved;

    private LocalDateTime dateOfRegister;
}
