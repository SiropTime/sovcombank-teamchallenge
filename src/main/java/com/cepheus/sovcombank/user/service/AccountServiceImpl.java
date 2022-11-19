package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.exception.BalanceException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.UserIsNotOwnerException;
import com.cepheus.sovcombank.user.dto.LogicDto;
import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.AccountRepository;
import com.cepheus.sovcombank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void transaction(LogicDto logicDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not find"));
        Account account = accountRepository.findById(logicDto.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account for buy not find"));
        Account accountRu = accountRepository.findByUserAndCurrency(user, Currency.RUB);
        if(!userId.equals(account.getId())){
            throw new UserIsNotOwnerException("User is not owner");
        }
        if(logicDto.getSum() > 0) {
            if(accountRu.getBalance() - logicDto.getSum() < 0 ){
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(accountRu.getBalance() - logicDto.getSum());
            account.setBalance(account.getBalance() + (account.getBalance() * logicDto.getCurrencyRatio()));
        } else {
            if(account.getBalance() - (logicDto.getSum() / logicDto.getCurrencyRatio()) > 0){
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(account.getBalance() - (logicDto.getSum() / logicDto.getCurrencyRatio()));
            account.setBalance(accountRu.getBalance() + (account.getBalance()));
        }
        accountRepository.save(account);
        accountRepository.save(accountRu);
    }
}
