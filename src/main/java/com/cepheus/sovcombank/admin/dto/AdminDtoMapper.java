package com.cepheus.sovcombank.admin.dto;


import com.cepheus.sovcombank.admin.model.Admin;

public class AdminDtoMapper {
    public static Admin dtoToAdmin(AdminDto adminDto){
        return Admin.builder()
                .id(null)
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .build();
    }
}
