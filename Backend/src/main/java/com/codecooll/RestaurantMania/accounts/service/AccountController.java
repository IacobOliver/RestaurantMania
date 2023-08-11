package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/account")
@CrossOrigin(origins = "http://localhost:5174/")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/post_user")
    public void registerNewUser(@RequestBody User user) {
        accountService.addNewUser(user);
    }

    @Transactional
    @GetMapping(path = "/getUser/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable Long user_id) {
        User acc = accountService.getAccountById(user_id);
        return ResponseEntity.ok(acc);
    }


    @GetMapping(path = "/getUserByEmail/{user_email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String user_email){
        return ResponseEntity.ok(accountService.getAccountByEmail(user_email));
    }

}

