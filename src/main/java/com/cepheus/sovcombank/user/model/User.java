package com.cepheus.sovcombank.user.model;


import javax.persistence.Column;
import javax.persistence.Id;

public class User {
    @Id
    private Long id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_banned")
    private Boolean banned;
}
