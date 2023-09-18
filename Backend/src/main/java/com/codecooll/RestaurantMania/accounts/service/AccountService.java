package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.Account;
import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.restaurant.service.menuService.MenuRepository;
import com.codecooll.RestaurantMania.restaurant.service.restaurantService.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.accountRepository = accountRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public void addNewUser(User user) {
        Optional<Account> accountByEmail = accountRepository.findAccountByEmail(user.getEmail());
        if (accountByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        accountRepository.save(user);
    }

    public User getAccountById(Long id) {
        Optional<Account> accountByID = accountRepository.findById(id);
        return (User) accountByID.orElse(null);
    }

    public User getAccountByEmail(String user_email){
        return (User) accountRepository.findAccountByEmail(user_email).orElse(null);

    }

}
