package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    @Query("SELECT NEW User(a.id,a.firstName,a.lastName,a.email,a.role) FROM Account a WHERE a.email = :email")
//    Optional<Account> findAccountByEmail(String email);
    @Query("SELECT u FROM User u JOIN FETCH u.restaurants r WHERE u.email = :email")
    Optional<Account> findAccountByEmail(String email);

}
