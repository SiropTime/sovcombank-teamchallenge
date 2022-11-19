package com.cepheus.sovcombank.user.controller;

import com.cepheus.sovcombank.user.dto.UserInputDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.cepheus.sovcombank.user.service.AccountService;
import com.cepheus.sovcombank.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/registration")
    public HttpStatus reqUser(@RequestBody UserInputDto userInputDto){
        userServiceImpl.reqUser(UserMapper.mapUserInputDtoToUser(userInputDto));
        return HttpStatus.OK;
    }

}
