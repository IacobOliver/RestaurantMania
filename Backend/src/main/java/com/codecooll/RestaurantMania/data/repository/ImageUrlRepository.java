package com.codecooll.RestaurantMania.data.repository;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ImageUrlRepository extends JpaRepository<Image, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Image r SET r.imageUrl = :newImageUrl WHERE r.id = :id")
    void updateImageById(Long id, String newImageUrl);

}
