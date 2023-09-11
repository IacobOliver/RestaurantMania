package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/account")
public class AccountController {
    private final AccountService accountService;
    private final JwtService jwtService;

    @Autowired
    public AccountController(AccountService accountService, JwtService jwtService) {
        this.accountService = accountService;
        this.jwtService = jwtService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/post_user")
    public void registerNewUser(@RequestBody User user) {
        accountService.addNewUser(user);
    }

    @CrossOrigin(origins = "*")
    @Transactional
    @GetMapping(path = "/getUser/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable Long user_id) {
        User acc = accountService.getAccountById(user_id);
        return ResponseEntity.ok(acc);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(path = "/getUserWithToken")
    public ResponseEntity<User> getUserWithToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        String user_email = jwtService.extractUsername(token);
        return ResponseEntity.ok(accountService.getAccountByEmail(user_email));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getUserByEmail/{user_email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String user_email) {
        return ResponseEntity.ok(accountService.getAccountByEmail(user_email));
    }

}

