package com.cepheus.sovcombank.account.controller;

import com.cepheus.sovcombank.account.dto.AccountActionDto;
import com.cepheus.sovcombank.account.dto.AccountDto;
import com.cepheus.sovcombank.account.dto.UserCheckAccountDto;
import com.cepheus.sovcombank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public HttpStatus create(@RequestBody AccountActionDto actionDto) {
        log.info("Создание валютного счёта в {} для пользователя {}", actionDto.getCurrency() ,actionDto.getEmail());
        accountService.createNewAccount(actionDto.getCurrency(), actionDto.getEmail());
        return HttpStatus.OK;
    }

    @DeleteMapping("/remove")
    public HttpStatus remove(@RequestBody AccountActionDto actionDto) {
        log.info("Удаление счёта в {} у пользователя {}", actionDto.getCurrency(), actionDto.getEmail());
        accountService.remove(actionDto.getCurrency(), actionDto.getEmail());
        return HttpStatus.OK;
    }

    @PostMapping("/account/check")
    public HttpStatus checkUserForAccount(@RequestBody UserCheckAccountDto userCheckAccountDto) {
        log.info("Проверка пользователя {} на принадлежность к счёту {}",
                userCheckAccountDto.getEmail(),userCheckAccountDto.getAccountId());
        accountService.checkUserAndAccount(userCheckAccountDto.getAccountId(),userCheckAccountDto.getEmail());
        return HttpStatus.OK;
    }

    @GetMapping("{email}")
    public List<AccountDto> getAccountsForUser(@PathVariable String email){
        return accountService.getAccountForUser(email);
    }

}