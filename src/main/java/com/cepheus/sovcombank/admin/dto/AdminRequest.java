package com.cepheus.sovcombank.admin.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class AdminRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
