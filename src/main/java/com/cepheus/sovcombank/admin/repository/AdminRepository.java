package com.cepheus.sovcombank.admin.repository;

import com.cepheus.sovcombank.admin.model.Admin;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
}
