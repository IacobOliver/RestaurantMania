package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT NEW User ( a.id,a.firstName,a.lastName,a.email,a.password,a.role) FROM User a WHERE a.email = :email")
    Optional<Account> findAccountByEmail(String email);

    @Query("SELECT NEW User ( a.id,a.firstName,a.lastName,a.email,a.role) FROM User a  WHERE a.email = :email")
    Optional<Account> findAccountByEmailForAutologin(String email);

}
