package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.model.Account;

public interface AccountService {
    Account createNewAccount(String currency, String email);
    // localHost::113/?name=1adfa
}
