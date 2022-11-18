package com.cepheus.sovcombank.user.repository;

import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
