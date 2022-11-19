package com.cepheus.sovcombank.user.controller;

import com.cepheus.sovcombank.user.dto.LogicDto;
import com.cepheus.sovcombank.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("{currency}")
    public HttpStatus create(@RequestBody String email,
                             @PathVariable String currency){
        accountService.createNewAccount(currency, email);
        return HttpStatus.OK;
    }

    @PutMapping("/transaction")
    public HttpStatus buy(@RequestBody LogicDto logicDto, @RequestParam Long userId){
        accountService.transaction(logicDto,userId);
        return HttpStatus.OK;
    }

}
