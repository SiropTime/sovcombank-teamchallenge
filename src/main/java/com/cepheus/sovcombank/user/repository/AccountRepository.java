package com.cepheus.sovcombank.user.repository;

import com.cepheus.sovcombank.user.model.Account;
import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(User user);
}
