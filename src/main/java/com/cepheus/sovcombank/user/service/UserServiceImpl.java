package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.exception.LogException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.user.dto.UserStartBalanceDto;
import com.cepheus.sovcombank.user.mapper.UserMapper;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.account.repository.AccountRepository;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public User reqUser(User user){
        user.setApproved(false);
        user.setBanned(false);
        user.setDateOfRegister(LocalDateTime.now());
        user.setBalance(0F);
        Account account = generateRuAccount(user);
        user.setAccounts(Arrays.asList(account));
        log.info("Пользователь {} создан с открытым счётом в рублях ", user.getEmail());
        accountRepository.save(account);
        return userRepository.save(user);
    }

    @Override
    public User log(User user){
        User userTest = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("Пользователь с почтой" + user.getEmail() + " не найден"));
        if(!userTest.getPassword().equals(user.getPassword())) {
            throw new LogException("Пароль и почта не совпадают");
        }
        log.info("Пользователь {}, успешно зашёл", user.getEmail());
        return user;
    }

    @Override
    public UserStartBalanceDto getBalance(String email) {
        User user = findByEmail(email);
        return UserMapper.mapUserToUserStart(user);
    }

    public User findByEmail(String email){
        return userRepository.findAllByEmail(email).orElseThrow(() ->
                new NotFoundException("Пользователь с почтой : " + email + " не был найден."));
    }
    public List<User> findAllUnconfirmed(int from,int size){
        return userRepository.findAllByApprovedIsFalse(PageRequest
                .of(from/size, size));
    }

    private Account generateRuAccount(User user){
        return Account.builder()
                .currency(Currency.RUB)
                .user(user)
                .balance(0F)
                .build();
    }

}
