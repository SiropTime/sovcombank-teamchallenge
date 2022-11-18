package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.AccountRepository;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public User reqUser(User user){
        user.setApproved(false);
        user.setDateOfRegister(LocalDateTime.now());
        Account account = generateRuAccount(user);
        accountRepository.save(account);
        return userRepository.save(user);
    }

    private Account generateRuAccount(User user){
        return Account.builder()
                .currency_key(Currency.RUB)
                .user(user)
                .balance(0F)
                .build();
    }

}
