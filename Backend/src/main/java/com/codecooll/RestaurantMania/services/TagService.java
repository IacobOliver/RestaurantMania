package com.codecooll.RestaurantMania.services;


import com.codecooll.RestaurantMania.data.repository.RestaurantRepository;
import com.codecooll.RestaurantMania.data.repository.TagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.model.Tag;
import com.codecooll.RestaurantMania.restaurant.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final RestaurantService restaurantService;

    public Tag addTagInRestaurant(Long restaurant_id, Long tag_id){
        Restaurant restaurant = restaurantService.getById(restaurant_id);
        Tag tag = tagRepository.findById(tag_id).orElse(null);

        if(restaurant != null && tag != null){
            restaurant.addTag(tag);
            tag.addRestaurant(restaurant);

        }
        return tag;
    }

    public List<Tag> getSomeTags(int pageNr, int quantity){
        Pageable pageable = PageRequest.of(pageNr, quantity);
        return tagRepository.getSomeTags(pageable);
    }
}