package com.cepheus.sovcombank.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserInputDto {
    private String name;
    private String email;
    private String password;
}
