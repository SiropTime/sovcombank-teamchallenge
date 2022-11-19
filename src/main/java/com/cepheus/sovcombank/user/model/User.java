package com.cepheus.sovcombank.user.model;


import com.cepheus.sovcombank.account.model.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@Table(name = "users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User  {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) && Objects.equals(banned, user.banned) &&
                Objects.equals(approved, user.approved) && Objects.equals(dateOfRegister, user.dateOfRegister);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, banned, approved, dateOfRegister);
    }
}
