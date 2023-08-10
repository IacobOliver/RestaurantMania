package com.codecooll.RestaurantMania.restaurant.service.ratingService;

import com.codecooll.RestaurantMania.restaurant.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
