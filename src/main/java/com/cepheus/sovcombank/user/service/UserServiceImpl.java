package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.AccountRepository;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
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
    public User findByEmail(String email){
        return userRepository.findAllByEmail(email).orElseThrow(() ->
                new NotFoundException("The user with the mail: " + email + " was not found."));
    }
    public List<User> findAllUnconfirmed(int from,int size){
        return userRepository.findAllUnconfirmed(PageRequest
                .of(from/size, size, Sort.by("date_of_register")));
    }

    private Account generateRuAccount(User user){
        return Account.builder()
                .currency_key(Currency.RUB)
                .user(user)
                .balance(0F)
                .build();
    }

}
