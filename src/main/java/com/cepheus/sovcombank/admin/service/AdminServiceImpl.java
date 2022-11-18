package com.cepheus.sovcombank.admin.service;

import com.cepheus.sovcombank.admin.model.Admin;
import com.cepheus.sovcombank.admin.repository.AdminRepository;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final AdminCodeService adminCodeService;

    @Override
    @Transactional
    public void add(Admin admin, String code) {
        if(adminCodeService.find(code)) {
            adminRepository.save(admin);
            adminCodeService.delete(code);
        }else {
            throw new ValidationException("Invalid invitation code: "+code);
        }
    }

    @Override
    public void login(String email,String password){
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("The administrator with the mail: " + email + " was not found."));
        if(!admin.getPassword().equals(password)){
            throw new NotFoundException("Invalid password.");
        }
    }

    @Override
    public String getByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("The administrator with the mail: " + email + " was not found."));
        return admin.getName();
    }
}
