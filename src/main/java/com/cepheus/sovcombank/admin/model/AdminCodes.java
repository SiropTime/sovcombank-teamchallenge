package com.cepheus.sovcombank.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_codes", schema = "public")
public class AdminCodes {
    @Id
    @Column(name = "code")
    private String code;
}
