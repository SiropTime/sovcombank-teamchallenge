package com.cepheus.sovcombank.admin.service;


import com.cepheus.sovcombank.admin.model.Admin;
import com.cepheus.sovcombank.user.model.User;

import java.util.List;

public interface AdminService {
    void add(Admin admin, String code);
    void login(String email,String password);
    String getByEmail(String email);
    void confirmationUser(String userEmail);
    List<User> findUnconfirmedUsers(int from, int size);
    void changingUserLock(String userEmail,boolean banned);
}
