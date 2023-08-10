package com.codecooll.RestaurantMania.restaurant.service.productService;

import com.codecooll.RestaurantMania.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
