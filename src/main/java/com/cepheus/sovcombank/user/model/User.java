package com.cepheus.sovcombank.user.model;


import com.cepheus.sovcombank.account.model.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_banned")
    private Boolean banned;

    @Column(name = "is_approved")
    private Boolean approved;

    @Column(name = "date_of_register")
    private LocalDateTime dateOfRegister;

    @OneToMany
    private List<Account> accounts;
}
