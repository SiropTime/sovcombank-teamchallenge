package com.cepheus.sovcombank.user.dto;

import com.cepheus.sovcombank.account.model.Account;

import java.util.List;

public class UserBoardDto {
    private Long id;
    private String name;
    private Boolean banned;
    private List<Account> accounts;
}
