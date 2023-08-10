package com.codecooll.RestaurantMania.restaurant.service.ratingService;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.accounts.service.AccountRepository;
import com.codecooll.RestaurantMania.restaurant.model.*;
import com.codecooll.RestaurantMania.restaurant.service.restaurantService.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository, AccountRepository accountRepository) {
        this.restaurantRepository = restaurantRepository;
        this.ratingRepository = ratingRepository;
        this.accountRepository  = accountRepository;
    }

    public List<Rating> getRestaurantRatings(){
        return ratingRepository.findAll();
    }



    public RestaurantRating addNewRestaurantRating(Long restaurant_id, RestaurantRating restaurantRating){

        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        restaurantRating.setRestaurant(restaurant);

        User user = (User)  accountRepository.findById(restaurant.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("USer not found"));

        restaurantRating.setUser(user);

        ratingRepository.save(restaurantRating);
        return  restaurantRating;
    }

}
