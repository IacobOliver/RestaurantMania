package com.codecooll.RestaurantMania.services;

import com.codecooll.RestaurantMania.data.repository.RestaurantTagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.model.RestaurantTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTagService {
    private final RestaurantTagRepository restaurantTagRepository;

    public List<RestaurantTag> findAllByRestaurant(long restaurantId) {
        Restaurant restaurant = Restaurant.builder().id(restaurantId).build();
        return restaurantTagRepository.findAllByRestaurant(restaurant);
    }
}
