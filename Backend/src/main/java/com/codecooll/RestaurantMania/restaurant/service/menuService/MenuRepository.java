package com.codecooll.RestaurantMania.restaurant.service.menuService;

import com.codecooll.RestaurantMania.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
