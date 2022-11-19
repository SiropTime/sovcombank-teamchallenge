package com.cepheus.sovcombank.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "accounts", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_key")
    private Currency currency_key;

    @ManyToOne
    private User user;

    @Column(name = "balance")
    private Float balance;

}
