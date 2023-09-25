package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("UPDATE Product p SET p.name = :name " +
            "WHERE p.id = :id")
    public void updateName(Long id, String name);

    @Query("UPDATE Product p SET p.name = :description " +
            "WHERE p.id = :id")
    public void updateDescription(Long id, String description);

    @Query("UPDATE Product p SET p.name = :price " +
            "WHERE p.id = :id")
    public void updatePrice(Long id, double price);
}
