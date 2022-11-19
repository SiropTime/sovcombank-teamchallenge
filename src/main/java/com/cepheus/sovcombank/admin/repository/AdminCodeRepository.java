package com.cepheus.sovcombank.admin.repository;


import com.cepheus.sovcombank.admin.model.AdminCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdminCodeRepository extends JpaRepository<AdminCodes, String> {
    @Query("select count(code)  from AdminCodes where code LIKE ?1")
    int contains(String code);

    void deleteByCode(String code);
}
