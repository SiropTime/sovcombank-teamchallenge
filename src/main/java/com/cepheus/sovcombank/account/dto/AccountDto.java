package com.cepheus.sovcombank.account.dto;

import com.cepheus.sovcombank.account.model.Currency;
import lombok.Builder;

@Builder
public class AccountDto {
    private Long id;
    private Currency currency;
    private Float balance;
}
