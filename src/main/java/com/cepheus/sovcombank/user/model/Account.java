package com.cepheus.sovcombank.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
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
