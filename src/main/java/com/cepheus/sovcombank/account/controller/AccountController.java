package com.cepheus.sovcombank.account.controller;

import com.cepheus.sovcombank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("{currency}")
    public HttpStatus create(@RequestBody String email,
                             @PathVariable String currency){
        accountService.createNewAccount(currency, email);
        return HttpStatus.OK;
    }

}
