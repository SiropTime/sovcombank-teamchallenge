package com.cepheus.sovcombank.account.model;

import com.cepheus.sovcombank.user.model.User;
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
    private Currency currency;

    @ManyToOne
    private User user;

    @Column(name = "balance")
    private Float balance;

}
