package com.cepheus.sovcombank.user.repository;

import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.Currency;
import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(User user);

    Account findByUserAndCurrency(User user, Currency rub);

    Optional<Account> findByIdAndUser(Integer accountId, User user);
}
