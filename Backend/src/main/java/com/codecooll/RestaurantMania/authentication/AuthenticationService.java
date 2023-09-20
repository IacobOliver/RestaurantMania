package com.codecooll.RestaurantMania.authentication;


import com.codecooll.RestaurantMania.restaurant.model.Account;
import com.codecooll.RestaurantMania.restaurant.types.Role;
import com.codecooll.RestaurantMania.restaurant.model.User;
import com.codecooll.RestaurantMania.data.repository.AccountRepository;
import com.codecooll.RestaurantMania.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        Optional<Account> accountByEmail = accountRepository.findAccountByEmail(user.getEmail());
        if (accountByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        accountRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        UserDetails user = accountRepository.findAccountByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return AuthenticationResponse.builder().token("Account  doesn't exists").build();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
