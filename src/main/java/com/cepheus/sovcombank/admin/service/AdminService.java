package com.cepheus.sovcombank.admin.service;


import com.cepheus.sovcombank.admin.model.Admin;

public interface AdminService {
    void add(Admin admin, String code);
    void login(String email,String password);
    String getByEmail(String email);
}
