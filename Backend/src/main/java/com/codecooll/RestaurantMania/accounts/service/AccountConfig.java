package com.codecooll.RestaurantMania.accounts.service;

import com.codecooll.RestaurantMania.Populate;
import com.codecooll.RestaurantMania.accounts.model.Account;
import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.restaurant.service.categoryProductService.CategoryProductService;
import com.codecooll.RestaurantMania.restaurant.service.productService.ProductService;
import com.codecooll.RestaurantMania.restaurant.service.restaurantService.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

//@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService, RestaurantService restaurantService, CategoryProductService categoryProductService, ProductService productService) {
        return args -> {
            Populate populate = new Populate(restaurantService,accountService, categoryProductService,productService);
            populate.populate();
            System.out.println("Salut");
        };
    }
}
