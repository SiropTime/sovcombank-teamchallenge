package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.AccountRepository;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Account createNewAccount(String currency, String email){
        User user = userRepository.findByEmail(email);
        List<Account> accounts = accountRepository.findAllByUser(user);
        Currency cur = Currency.valueOf(currency);
        for(Account element: accounts){
            if(element.getCurrency_key().equals(cur)){
                throw new NotFoundException("Account have in this user");
            }
        }
        Account account = Account.builder()
                .currency_key(cur)
                .balance(0F)
                .user(user)
                .build();
        return accountRepository.save(account);
    }
}
