package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.dto.LogicDto;
import com.cepheus.sovcombank.user.model.Account;

public interface AccountService {
    Account createNewAccount(String currency, String email);

    void transaction(LogicDto logicDto, Long userId);
}
