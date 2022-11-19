package com.cepheus.sovcombank.admin.controller;

import com.cepheus.sovcombank.admin.dto.AdminDto;
import com.cepheus.sovcombank.admin.dto.AdminDtoMapper;
import com.cepheus.sovcombank.admin.dto.AdminRequest;
import com.cepheus.sovcombank.admin.service.AdminService;
import com.cepheus.sovcombank.exception.ValidationException;
import com.cepheus.sovcombank.user.dto.UserFoAdminDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

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

    @PutMapping(path = "/confirmation/{userEmail}")
    public HttpStatus confirmationStatus(@PathVariable String userEmail){
        adminService.confirmationUser(userEmail);
        return HttpStatus.OK;
    }
    @GetMapping(path = "/confirmation")
    public List<UserFoAdminDto> findAllConfirmation(@RequestParam(defaultValue = "1") Integer from,
                                                    @RequestParam(defaultValue = "10") Integer size){
        if (from < 0 || size == 0) {
            throw new ValidationException("Incorrect request parameters were passed: start from the page " + from +
                    " ,number of elements on the page " + size);
        }
        return adminService.findUnconfirmedUsers(from,size).stream().map(UserMapper::mapUserFoAdmin)
                .collect(Collectors.toList());
    }
    @PutMapping(path = "/banned/{userEmail}/?{banned}")
    public HttpStatus changingUserLock(@PathVariable String userEmail,
                                       @PathVariable @NotNull Boolean banned){
        adminService.changingUserLock(userEmail,banned);
        return HttpStatus.OK;
    }
}
