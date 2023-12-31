package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.name = :name " +
            "WHERE p.id = :id")
    public void updateName(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.productDescription = :description " +
            "WHERE p.id = :id")
    public void updateDescription(Long id, String description);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = :price " +
            "WHERE p.id = :id")
    public void updatePrice(Long id, double price);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.image = :image WHERE p.id = :id")
    void updateImageById(Long id, Image image);

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :product_id" )
    public void deleteProductById(Long product_id);
}
