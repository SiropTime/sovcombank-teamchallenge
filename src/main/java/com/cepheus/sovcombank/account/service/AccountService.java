package com.cepheus.sovcombank.account.service;

import com.cepheus.sovcombank.account.model.Account;

public interface AccountService {
    Account createNewAccount(String currency, String email);

}
