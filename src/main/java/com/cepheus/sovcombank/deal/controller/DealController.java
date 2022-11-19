package com.cepheus.sovcombank.deal.controller;

import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.service.DealService;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping("/deal/accounts")
    public HttpStatus deal(@RequestBody ForDealDto forDealDto, @RequestParam Long userId){
        dealService.make(forDealDto,userId);
        return HttpStatus.OK;
    }

    @PostMapping("/deal/balance")
    public HttpStatus changeBalance(@RequestBody BalanceChangerDto balanceChangerDto,
                                    @RequestParam Long userId) {
        dealService.changeBalance(balanceChangerDto, userId);
        return HttpStatus.OK;
    }
}
