package com.cepheus.sovcombank.user.controller;

import com.cepheus.sovcombank.user.dto.UserInputDto;
import com.cepheus.sovcombank.user.dto.UserLogDto;
import com.cepheus.sovcombank.user.dto.UserStartBalanceDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userServiceImpl;

    @PostMapping("/registration")
    public HttpStatus reqUser(@Validated @RequestBody UserInputDto userInputDto){
        log.info("Пользователь с почтой {} пытается зарегистрироваться", userInputDto.getEmail());
        userServiceImpl.reqUser(UserMapper.mapUserInputDtoToUser(userInputDto));
        return HttpStatus.OK;
    }

    @PostMapping("/login")
    public HttpStatus log(@RequestBody UserLogDto user) {
        log.info("Пользователь с почтой {}, пытается зайти", user.getEmail());
        userServiceImpl.log(UserMapper.mapUserLogDtoToUser(user));
        return HttpStatus.OK;
    }

    @GetMapping("/user")
    public UserStartBalanceDto getBalance(@RequestParam String email){
        log.info("Получение данных о начальном капитале");
        return userServiceImpl.getBalance(email);
    }
}
