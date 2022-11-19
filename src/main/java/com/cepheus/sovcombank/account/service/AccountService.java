package com.cepheus.sovcombank.account.service;

import com.cepheus.sovcombank.account.model.Account;

public interface AccountService {
    Account createNewAccount(String currency, String email);

    void remove(String currency, String email);

    void checkUserAndAccount(Long accountId, String email);
}
