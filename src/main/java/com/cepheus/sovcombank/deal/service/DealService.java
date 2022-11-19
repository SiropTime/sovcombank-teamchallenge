package com.cepheus.sovcombank.deal.service;

import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import com.cepheus.sovcombank.deal.model.Deal;

public interface DealService {
    Deal make(ForDealDto forDealDto, Long userId);

    Deal changeBalance(BalanceChangerDto balanceChangerDto, Long userId);
}
