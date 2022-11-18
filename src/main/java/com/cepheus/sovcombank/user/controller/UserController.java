package com.cepheus.sovcombank.user.controller;

import com.cepheus.sovcombank.user.dto.UserInputDto;
import com.cepheus.sovcombank.user.dto.UserOutputDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public HttpStatus reqUser(UserInputDto userInputDto){
        userService.reqUser(UserMapper.mapUserInputDtoToUser(userInputDto));
        return HttpStatus.OK;
    }
}
