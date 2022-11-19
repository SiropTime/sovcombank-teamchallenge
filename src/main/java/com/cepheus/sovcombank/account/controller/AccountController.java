package com.cepheus.sovcombank.account.controller;

import com.cepheus.sovcombank.account.dto.UserCheckAccountDto;
import com.cepheus.sovcombank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create{currency}")
    public HttpStatus create(@RequestBody String email,
                             @PathVariable String currency) {
        log.info("Создание валютного счёта в {} для пользователя {}", currency ,email);
        accountService.createNewAccount(currency, email);
        return HttpStatus.OK;
    }

    @DeleteMapping("/remove{currency}")
    public HttpStatus remove(@RequestBody String email,
                             @PathVariable String currency) {
        log.info("Удаление счёта в {} у пользователя {}", currency, email);
        accountService.remove(currency, email);
        return HttpStatus.OK;
    }

    @PostMapping("account/check")
    public HttpStatus checkUserForAccount(@RequestBody UserCheckAccountDto userCheckAccountDto) {
        log.info("Проверка пользователя {} на принадлежность к счёту {}",
                userCheckAccountDto.getEmail(),userCheckAccountDto.getAccountId());
        accountService.checkUserAndAccount(userCheckAccountDto.getAccountId(),userCheckAccountDto.getEmail());
        return HttpStatus.OK;
    }

}
