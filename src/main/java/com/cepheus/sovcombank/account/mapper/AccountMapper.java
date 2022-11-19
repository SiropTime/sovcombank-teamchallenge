package com.cepheus.sovcombank.account.mapper;

import com.cepheus.sovcombank.account.dto.AccountDto;
import com.cepheus.sovcombank.account.model.Account;

public class AccountMapper {
    public static AccountDto mapAccountToAccountDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .build();
    }
}
