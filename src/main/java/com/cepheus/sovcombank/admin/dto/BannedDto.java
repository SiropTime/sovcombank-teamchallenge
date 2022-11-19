package com.cepheus.sovcombank.admin.dto;

import lombok.Data;


@Data
public class BannedDto {
    private String userEmail;
    private Boolean banned;
}
