package com.cepheus.sovcombank.admin.controller;

import com.cepheus.sovcombank.admin.dto.*;
import com.cepheus.sovcombank.admin.service.AdminService;
import com.cepheus.sovcombank.exception.ValidationException;
import com.cepheus.sovcombank.user.dto.UserFoAdminDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admins")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @PostMapping(path = "/registration")
    public HttpStatus add(@RequestBody AdminDto adminDto) {
        log.info("Регистрация админа {}", adminDto.getEmail());
        adminService.add(AdminDtoMapper.dtoToAdmin(adminDto), adminDto.getCode());
        return HttpStatus.OK;
    }

    @PostMapping(path = "/login")
    public HttpStatus login(@RequestBody AdminRequest adminRequest) {
        log.info("Попытка входа {} в систему", adminRequest.getEmail());
        adminService.login(adminRequest.getEmail(), adminRequest.getPassword());
        return HttpStatus.OK;
    }


    @PutMapping(path = "/confirmation")
    public HttpStatus confirmationStatus(@RequestBody EmailDto userEmail) {
        log.info("Потверждение пользователя {}", userEmail);
        adminService.confirmationUser(userEmail.getEmail());
        return HttpStatus.OK;
    }

    @GetMapping(path = "/confirmation")
    public List<UserFoAdminDto> findAllConfirmation(@RequestParam(defaultValue = "1") Integer from,
                                                    @RequestParam(defaultValue = "10") Integer size) {
        if (from < 0 || size == 0) {
            throw new ValidationException("Incorrect request parameters were passed: start from the page " + from +
                    " ,number of elements on the page " + size);
        }
        log.info("Получение списка пользователей");
        return adminService.findUnconfirmedUsers(from, size).stream().map(UserMapper::mapUserFoAdmin)
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/banned")
    public HttpStatus changingUserLock(@RequestBody BannedDto banned) {
        log.info("Блокировка {}", banned);
        adminService.changingUserLock(banned.getUserEmail(), banned.getBanned());
        return HttpStatus.OK;
    }
    @PutMapping(path = "/post/delete")
    public HttpStatus deletePostById(@RequestParam Long postId){
        adminService.deletePostById(postId);
        return HttpStatus.OK;
    }
}
