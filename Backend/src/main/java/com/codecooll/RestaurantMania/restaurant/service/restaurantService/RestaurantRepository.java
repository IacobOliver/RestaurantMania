package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT NEW com.codecooll.RestaurantMania.restaurant.model.Restaurant( r.id, r.name, r.rating, r.image, r.description, r.active, r.address) " +
            "FROM Restaurant r LEFT JOIN r.image i")
    List<Restaurant> findAllRestaurantsWithNamesAndRatings();

    @Query("SELECT NEW com.codecooll.RestaurantMania.restaurant.model.Restaurant( r.id,r.name, r.rating, r.image, r.description, r.active, r.address) " +
            "FROM Restaurant r LEFT JOIN r.image i " +
            "WHERE r.user.id = :user_id")
    List<Restaurant> findAllRestaurantsOfUser(long user_id);

    @Query("SELECT NEW Restaurant(r.id, r.name, r.rating, r.image, r.description, r.active, r.address) " +
            "FROM Restaurant r " +
            "WHERE r.id = :restaurant_id")
    Optional<Restaurant> findByIdWithoutMenu(long restaurant_id);
}
