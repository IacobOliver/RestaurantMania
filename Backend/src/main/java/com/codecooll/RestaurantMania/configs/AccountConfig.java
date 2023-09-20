package com.codecooll.RestaurantMania.configs;

import com.codecooll.RestaurantMania.Populate;
import com.codecooll.RestaurantMania.services.AccountService;
import com.codecooll.RestaurantMania.services.CategoryProductService;
import com.codecooll.RestaurantMania.services.ProductService;
import com.codecooll.RestaurantMania.services.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

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
