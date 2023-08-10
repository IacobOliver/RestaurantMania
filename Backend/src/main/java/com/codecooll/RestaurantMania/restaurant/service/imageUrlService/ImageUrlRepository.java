package com.codecooll.RestaurantMania.restaurant.service.imageUrlService;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUrlRepository extends JpaRepository<Image, Long> {
}
