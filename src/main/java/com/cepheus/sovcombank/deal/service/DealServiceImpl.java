package com.cepheus.sovcombank.deal.service;

import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.account.repository.AccountRepository;
import com.cepheus.sovcombank.account.service.AccountService;
import com.cepheus.sovcombank.deal.dto.BalanceChangerDto;
import com.cepheus.sovcombank.deal.dto.DealMapper;
import com.cepheus.sovcombank.deal.dto.DealOutputDto;
import com.cepheus.sovcombank.deal.dto.ForDealDto;
import com.cepheus.sovcombank.deal.model.Deal;
import com.cepheus.sovcombank.deal.repository.DealRepository;
import com.cepheus.sovcombank.exception.BalanceException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.UserIsNotOwnerException;
import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DealServiceImpl implements DealService {
    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final DealRepository dealRepository;

    @Transactional
    @Override
    public Deal make(ForDealDto forDealDto, String email) {
        User user = userService.findByEmail(email);
        Account account = accountService.findById(forDealDto.getAccountId());
        Account accountRu = accountService.findByUserAndCurrency(user, Currency.RUB);
        if (!user.getId().equals(account.getId())) {
            throw new UserIsNotOwnerException("User is not owner");
        }
        if (forDealDto.getSum() > 0) {
            if (accountRu.getBalance() - forDealDto.getSum() < 0) {
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(accountRu.getBalance() - forDealDto.getSum());
            account.setBalance(account.getBalance() + (account.getBalance() * forDealDto.getCurrencyRatio()));
        } else {
            if (account.getBalance() - (forDealDto.getSum() / forDealDto.getCurrencyRatio()) > 0) {
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(account.getBalance() - (forDealDto.getSum() / forDealDto.getCurrencyRatio()));
            account.setBalance(accountRu.getBalance() + (account.getBalance()));
        }
        Deal deal = Deal.builder()
                .timeStamp(LocalDateTime.now())
                .summary(-1 * forDealDto.getSum())
                .account(accountRu)
                .build();
        dealRepository.save(deal);
        accountRepository.save(account);
        accountRepository.save(accountRu);
        log.info("Покупка валюты {}, пользователем {}, на сумму {}. Прошла успешно",
                account.getCurrency(), user.getEmail(), forDealDto.getSum());
        return deal;
    }

    @Transactional
    @Override
    public Deal changeBalance(BalanceChangerDto balanceChangerDto, String email) {
        User user = userService.findByEmail(email);
        Account account = accountService.findByUserAndCurrency(user, Currency.RUB);
        account.setBalance(account.getBalance() + balanceChangerDto.getSum());
        Deal deal = Deal.builder()
                .summary(balanceChangerDto.getSum())
                .timeStamp(LocalDateTime.now())
                .build();
        accountRepository.save(account);
        log.info("На баланс {} успешно пришло {}", user.getEmail(), balanceChangerDto.getSum());
        return dealRepository.save(deal);
    }

    @Override
    public List<DealOutputDto> findHistoryByTime(String email, Long accountId, LocalDate from, LocalDate to) {
        accountService.checkUserAndAccount(accountId, email);
        Account account = accountService.findById(accountId);
        List<Deal> deals;
        if (to != null) {
            deals = dealRepository.findAllByAccount(account, from, to);
        } else {
            deals = dealRepository.findAllByAccount(account, from);
        }
        if(deals.isEmpty()){
            throw new NotFoundException("No operations were performed during the specified time");
        }else {
            return deals.stream().map(DealMapper::mapDealToOut)
                    .collect(Collectors.toList());
        }
    }
}
