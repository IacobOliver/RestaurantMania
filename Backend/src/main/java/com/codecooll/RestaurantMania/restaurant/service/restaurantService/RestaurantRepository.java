package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
