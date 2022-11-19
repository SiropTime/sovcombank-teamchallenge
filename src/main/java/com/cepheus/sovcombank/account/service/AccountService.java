package com.cepheus.sovcombank.account.service;

import com.cepheus.sovcombank.account.dto.AccountDto;
import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.user.model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createNewAccount(String currency, String email);

    void remove(String currency, String email);

    void checkUserAndAccount(Long accountId, String email);

    List<AccountDto> getAccountForUser(String email);
    Account findByUserAndCurrency(User user, Currency currency);
    Account findById(Long id);
}
