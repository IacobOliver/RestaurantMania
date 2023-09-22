package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {


    @Query("SELECT NEW Menu(m.id, m.lastUpdated)" +
            " FROM Menu m" +
            " WHERE m.restaurant.id = :restaurant_id ")
    Optional<Menu> getByRestaurantIdWithoutCategoryProducts(long restaurant_id);
}
