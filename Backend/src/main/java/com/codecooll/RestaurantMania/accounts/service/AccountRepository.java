package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByEmail(String email);
}
