package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    @Query("SELECT c FROM CategoryProduct c " +
            "WHERE c.menu.id = :menuId " +
            "order by c.id asc"
           )
    List<CategoryProduct> getSomeOfMenu(long menuId, Pageable pageable);


    @Modifying
    @Transactional
    @Query("UPDATE CategoryProduct c SET c.name = :newName WHERE c.id = :id")
    public void updateNameById(Long id, String newName);







}
