package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.model.Account;

public interface AccountService {
    Account createNewAccount(String currency, Long userId);
    // localHost::113/?name=1adfa
}
