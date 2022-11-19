package com.cepheus.sovcombank.account.service;

import com.cepheus.sovcombank.account.dto.AccountDto;
import com.cepheus.sovcombank.account.mapper.AccountMapper;
import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.account.repository.AccountRepository;
import com.cepheus.sovcombank.exception.BalanceException;
import com.cepheus.sovcombank.exception.ForbiddenException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Account createNewAccount(String currency, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь с почтой" + email + " не найден"));
        List<Account> accounts = accountRepository.findAllByUser(user);
        Currency cur = Currency.valueOf(currency);
        for (Account element : accounts) {
            if (element.getCurrency().equals(cur)) {
                throw new NotFoundException("Счёт с валютой " + currency + " уже существует");
            }
        }
        Account account = Account.builder()
                .currency(cur)
                .balance(0F)
                .user(user)
                .build();
        log.info("Добавление пользователю {} новый валютный счёт в {}", email, currency);
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public void remove(String currency, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь с почтой" + email + " не найден"));
        Account account = accountRepository.findByUserAndCurrency(user, Currency.valueOf(currency))
                .orElseThrow(() -> new NotFoundException("Счёт с валютой " + currency + " Не найден"));
        if (account.getBalance() > 0) {
            throw new BalanceException("Баланс на счёте " + currency + " Не нулевой");
        }
        accountRepository.delete(account);
        log.info("Успешное удаление счёта в " + currency);
    }

    @Override
    public void checkUserAndAccount(Long accountId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь с почтой" + email + " не найден"));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Счёт с айди " + accountId + " не найден"));
        if (!user.getId().equals(account.getUser().getId())) {
            throw new ForbiddenException("Счёт с айди " + accountId + " не принадлижит пользователю");
        }
    }

    @Override
    public List<AccountDto> getAccountForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь с почтой " + email + " не найден"));
        return accountRepository.findAllByUser(user)
                .stream()
                .map(AccountMapper::mapAccountToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public Account findByUserAndCurrency(User user, Currency currency) {
        return accountRepository.findByUserAndCurrency(user, Currency.RUB)
                .orElseThrow(() -> new NotFoundException("Счёт с валютой " + Currency.RUB + " Не найден"));
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id: "+id +" not find."));
    }

}
