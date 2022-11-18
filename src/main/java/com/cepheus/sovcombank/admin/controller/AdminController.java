package com.cepheus.sovcombank.admin.controller;

import com.cepheus.sovcombank.admin.dto.AdminDto;
import com.cepheus.sovcombank.admin.dto.AdminDtoMapper;
import com.cepheus.sovcombank.admin.dto.AdminRequest;
import com.cepheus.sovcombank.admin.service.AdminService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admins")
public class AdminController {
    private final AdminService adminService;
    @PostMapping(path = "/register")
    public HttpStatus add(AdminDto adminDto){
        adminService.add(AdminDtoMapper.dtoToAdmin(adminDto),adminDto.getCode());
        return HttpStatus.OK;
    }
    @GetMapping(path = "/login")
    public HttpStatus login(AdminRequest adminRequest){
        adminService.login(adminRequest.getEmail(),adminRequest.getPassword());
        return HttpStatus.OK;
    }
    @GetMapping(path = "/{email}")
    public String getByEmail(@PathVariable String email){
        return adminService.getByEmail(email);
    }

    @PutMapping()
    public HttpStatus confirmationStatus(){

        return HttpStatus.OK;
    }
}
