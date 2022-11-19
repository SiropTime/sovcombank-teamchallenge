package com.cepheus.sovcombank.deal.service;

import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import com.cepheus.sovcombank.deal.model.Deal;

public interface DealService {
    Deal make(ForDealDto forDealDto, String email);

    Deal changeBalance(BalanceChangerDto balanceChangerDto, String email);
}
