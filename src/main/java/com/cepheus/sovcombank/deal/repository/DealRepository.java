package com.cepheus.sovcombank.deal.repository;

import com.cepheus.sovcombank.deal.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
