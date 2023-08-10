package com.codecooll.RestaurantMania.restaurant.service.categoryProductService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
