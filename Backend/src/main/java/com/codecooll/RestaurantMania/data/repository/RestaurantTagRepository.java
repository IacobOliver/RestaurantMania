package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.model.RestaurantTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTagRepository extends JpaRepository<RestaurantTag, Long> {

    List<RestaurantTag> findAllByRestaurant(Restaurant restaurant);
}
