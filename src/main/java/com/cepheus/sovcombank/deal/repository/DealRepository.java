package com.cepheus.sovcombank.deal.repository;

import com.cepheus.sovcombank.account.model.Account;
import com.cepheus.sovcombank.deal.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    @Query("select d from Deal d where (d.account=?1 and  day(d.timeStamp) between ?2 and ?3) order by d.timeStamp")
    List<Deal> findAllByAccount(Account account, LocalDate from, LocalDate to);
    @Query("select d from Deal d where d.account=?1 and day(d.timeStamp)=?1")
    List<Deal> findAllByAccount(Account account, LocalDate from);
}
