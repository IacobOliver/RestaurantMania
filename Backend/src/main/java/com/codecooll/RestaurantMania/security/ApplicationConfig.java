package com.codecooll.RestaurantMania.security;

import com.codecooll.RestaurantMania.accounts.service.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AccountRepository accountRepository;

    @Bean
    public UserDetailsService userDetailsService (){
        return username -> accountRepository.findAccountByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
