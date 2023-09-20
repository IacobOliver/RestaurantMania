package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
//    @Query("SELECT NEW com.codecooll.RestaurantMania.restaurant.model.Restaurant(r.name, r.rating, r.image, r.description, r.active, r.id, r.address) " +
//            "FROM Restaurant r LEFT JOIN r.image i " +
//            "WHERE r.user.id = :user_id")
//    List<Restaurant> findAllRestaurantsOfUser(long user_id);

    @Query("SELECT c FROM CategoryProduct c " +
            "WHERE c.menu.id = :menuId "
           )
    List<CategoryProduct> getSomeOfMenu(long menuId, Pageable pageable);


}
