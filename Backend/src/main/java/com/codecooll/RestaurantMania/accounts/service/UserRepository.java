package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.Account;
import com.codecooll.RestaurantMania.accounts.model.Role;
import com.codecooll.RestaurantMania.accounts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT New com.codecooll.RestaurantMania.accounts.model.User(u.id,u.firstName,u.lastName,u.email,u.role) " +
            "FROM User u " +
            "WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
}
