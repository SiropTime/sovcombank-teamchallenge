package com.cepheus.sovcombank.deal.service;

import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.dto.DealOutputDto;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import com.cepheus.sovcombank.deal.model.Deal;

import java.time.LocalDate;
import java.util.List;

public interface DealService {
    Deal make(ForDealDto forDealDto, String email);

    Deal changeBalance(BalanceChangerDto balanceChangerDto, String email);
    List<DealOutputDto> findHistoryByTime(String email, Long accountId, LocalDate from, LocalDate to);

}
