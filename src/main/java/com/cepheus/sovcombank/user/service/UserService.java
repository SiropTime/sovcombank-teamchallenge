package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User reqUser(User user);
    User findByEmail(String email);
    List<User> findAllUnconfirmed(int from,int size);
}
