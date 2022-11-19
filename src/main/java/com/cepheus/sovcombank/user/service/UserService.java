package com.cepheus.sovcombank.user.service;

import com.cepheus.sovcombank.user.dto.UserStartBalanceDto;
import com.cepheus.sovcombank.user.model.User;

import java.util.List;

public interface UserService {
    User reqUser(User user);
    User findByEmail(String email);
    List<User> findAllUnconfirmed(int from,int size);

    User log(User user);

    UserStartBalanceDto getBalance(String email);
}
