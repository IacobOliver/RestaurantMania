package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.data.repository.RestaurantTagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.model.RestaurantTag;
import com.codecooll.RestaurantMania.services.RestaurantTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantTags")
public class RestaurantTagController {
    private final RestaurantTagService restaurantTagService;
    @CrossOrigin("*")
    @GetMapping("/{restaurantId}")
    public List<RestaurantTag> get(@PathVariable Long restaurantId){
        return restaurantTagService.findAllByRestaurant(restaurantId);
    }
}
