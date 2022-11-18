package com.cepheus.sovcombank.admin.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class AdminDto {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String code;
}
