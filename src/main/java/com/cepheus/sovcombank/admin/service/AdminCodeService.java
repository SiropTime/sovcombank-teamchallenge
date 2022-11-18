package com.cepheus.sovcombank.admin.service;

import com.cepheus.sovcombank.admin.repository.AdminCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCodeService {
    private final AdminCodeRepository adminCodeRepository;

    public boolean find(String code) {
        return adminCodeRepository.contains(code) == 1;
    }

    public void delete(String code) {
        adminCodeRepository.contains(code);
    }
}
