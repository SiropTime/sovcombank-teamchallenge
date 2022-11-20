package com.cepheus.sovcombank.deal.service;

import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.account.repository.AccountRepository;
import com.cepheus.sovcombank.account.service.AccountService;
import com.cepheus.sovcombank.deal.dto.*;
import com.cepheus.sovcombank.deal.model.Deal;
import com.cepheus.sovcombank.deal.model.Operation;
import com.cepheus.sovcombank.deal.repository.DealRepository;
import com.cepheus.sovcombank.exception.BalanceException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.UnauthorizedException;
import com.cepheus.sovcombank.exception.UserIsNotOwnerException;
import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.repository.UserRepository;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DealServiceImpl implements DealService {
    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Deal make(ForDealDto forDealDto, String email) {
        User user = userService.findByEmail(email);
        if (!user.getApproved() || user.getBanned()) {
            throw new UnauthorizedException("The user is not affected or banned");
        }
        Account account = accountService.findById(forDealDto.getAccountId());
        Account accountRu = accountService.findByUserAndCurrency(user, Currency.RUB);
        if (!user.getId().equals(account.getUser().getId())) {
            throw new UserIsNotOwnerException("User is not owner");
        }
        Operation operation;
        if (forDealDto.getSum() > 0) { // + к ру кошельку
            if (account.getBalance() - (forDealDto.getSum() * forDealDto.getCurrencyRatio()) < 0) {
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(accountRu.getBalance() + forDealDto.getSum());
            account.setBalance(account.getBalance() - (forDealDto.getSum() * forDealDto.getCurrencyRatio()));
            operation = Operation.SaleCurrency;
        } else { // Операция продажи с ру счёта
            if (accountRu.getBalance() - abs(forDealDto.getSum()) < 0) {
                throw new BalanceException("You can't take off more than you have");
            }
            accountRu.setBalance(accountRu.getBalance() - abs(forDealDto.getSum()));
            account.setBalance(account.getBalance() + abs((forDealDto.getSum() * forDealDto.getCurrencyRatio())));
            operation = Operation.PurchaseCurrency;
        }
        Deal deal = Deal.builder()
                .timeStamp(LocalDateTime.now())
                .summary(-1 * forDealDto.getSum())
                .account(account)
                .operation(operation)
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
        if (!user.getApproved() || user.getBanned()) {
            throw new UnauthorizedException("The user is not affected or banned");
        }
        Account account = accountService.findByUserAndCurrency(user, Currency.RUB);
        Operation operation = balanceChangerDto.getSum() < 0 ? Operation.WithdrawalBalance : Operation.ReplenishmentBalance;
        if (balanceChangerDto.getSum() < 0 && account.getBalance() - balanceChangerDto.getSum() < 0) {
            throw new BalanceException("На счёте меньше, чем в счёте");
        }
        account.setBalance(account.getBalance() + balanceChangerDto.getSum());
        Deal deal = Deal.builder()
                .summary(balanceChangerDto.getSum())
                .timeStamp(LocalDateTime.now())
                .operation(operation)
                .build();
        accountRepository.save(account);
        userRepository.save(user);
        log.info("На баланс {} успешно пришло {}", user.getEmail(), balanceChangerDto.getSum());
        return dealRepository.save(deal);
    }

    @Override
    public List<DealOutputDto> findHistoryByTime(String email, Long accountId) {
        accountService.checkUserAndAccount(accountId, email);
        Account account = accountService.findById(accountId);
        List<Deal> deals = dealRepository.findAllByAccount(account);

        if (deals.isEmpty()) {
            throw new NotFoundException("No operations were performed during the specified time");
        } else {
            return deals.stream().map(DealMapper::mapDealToOut)
                    .collect(Collectors.toList());
        }
    }
}
