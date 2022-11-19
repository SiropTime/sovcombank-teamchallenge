package com.cepheus.sovcombank.account.service;

import com.cepheus.sovcombank.account.dto.AccountDto;
import com.cepheus.sovcombank.account.model.Account;

import java.util.List;

public interface AccountService {
    Account createNewAccount(String currency, String email);

    void remove(String currency, String email);

    void checkUserAndAccount(Long accountId, String email);

    List<AccountDto> getAccountForUser(String email);
}
