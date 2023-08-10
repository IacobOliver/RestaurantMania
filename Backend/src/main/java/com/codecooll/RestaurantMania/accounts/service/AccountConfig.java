package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.Account;
import com.codecooll.RestaurantMania.accounts.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

//@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
        return args -> {

            Optional<Account> accountByEmail = accountRepository.findAccountByEmail("ionutGorceaa@gmail.com");
            if (accountByEmail.isPresent()) {
                User newAcc = (User) accountByEmail.get();
                System.out.println("Salut");
                System.out.println(newAcc.getRestaurants().get(0).getName());
            }
        };
    }
}
