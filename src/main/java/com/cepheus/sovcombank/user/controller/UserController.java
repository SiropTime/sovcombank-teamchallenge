package com.cepheus.sovcombank.user.controller;

import com.cepheus.sovcombank.user.dto.UserInputDto;
import com.cepheus.sovcombank.user.dto.UserLogDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userServiceImpl;

    @PostMapping("/registration")
    public HttpStatus reqUser(@RequestBody UserInputDto userInputDto){
        userServiceImpl.reqUser(UserMapper.mapUserInputDtoToUser(userInputDto));
        return HttpStatus.OK;
    }

    @PostMapping("/log")
    public HttpStatus log(@RequestBody UserLogDto user) {
        userServiceImpl.log(UserMapper.mapUserLogDtoToUser(user));
        return HttpStatus.OK;
    }

}
