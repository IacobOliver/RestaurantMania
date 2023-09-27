package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTagRepository extends JpaRepository<Tag, Long> {


}
