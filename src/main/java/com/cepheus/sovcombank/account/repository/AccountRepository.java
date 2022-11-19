package com.cepheus.sovcombank.account.repository;

import com.cepheus.sovcombank.account.model.Currency;
import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(User user);

    Optional<Account> findByUserAndCurrency(User user, Currency rub);

    Optional<Account> findByIdAndUser(Integer accountId, User user);
}
