package com.cepheus.sovcombank.user.controller;

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
    public HttpStatus create(@PathVariable String currency,
                             @RequestParam String email){
        accountService.createNewAccount(currency, email);
        return HttpStatus.OK;
    }
}
