package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.restaurant.model.Rating;
import com.codecooll.RestaurantMania.restaurant.model.RestaurantRating;
import com.codecooll.RestaurantMania.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/rating")
public class RatingController {
    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getRestaurantRatings")
    public ResponseEntity<List<Rating>> getALlRatings(){
       return ResponseEntity.ok(  ratingService.getRestaurantRatings());
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/post/restaurantRating/{restaurant_id}")
    public ResponseEntity<RestaurantRating> postRestaurantRating(@PathVariable Long restaurant_id,@RequestBody RestaurantRating restaurantRating){
        return ResponseEntity.ok(ratingService.addNewRestaurantRating(restaurant_id,restaurantRating));
    }


}
