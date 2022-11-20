package com.cepheus.sovcombank.deal.repository;

import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.deal.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findAllByAccount(Account account);
}
