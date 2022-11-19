package com.cepheus.sovcombank.user.repository;

import com.cepheus.sovcombank.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByEmail(String email);
    @Query("select u from User u where u.approved=false order by u.dateOfRegister")
    List<User> findAllByApprovedIsFalse(Pageable pageable);

    Optional<User> findByEmail(String email);
}
