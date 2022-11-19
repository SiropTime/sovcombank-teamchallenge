package com.cepheus.sovcombank.deal.controller;

import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.service.DealService;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DealController {
    private final DealService dealService;

    @PostMapping("/deal/accounts")
    public HttpStatus deal(@RequestBody ForDealDto forDealDto, @RequestParam String email){
        log.info("Начало операции с суммой {}, кэфом {} по сделке пользователя {}",
                forDealDto.getSum(),forDealDto.getCurrencyRatio(),forDealDto.getAccountId());
        dealService.make(forDealDto,email);
        return HttpStatus.OK;
    }

    @PostMapping("/deal/balance")
    public HttpStatus changeBalance(@RequestBody BalanceChangerDto balanceChangerDto,
                                    @RequestParam String email) {
        log.info("На средсва {} поступает платёж в {}", email, balanceChangerDto.getSum());
        dealService.changeBalance(balanceChangerDto, email);
        return HttpStatus.OK;
    }
}
