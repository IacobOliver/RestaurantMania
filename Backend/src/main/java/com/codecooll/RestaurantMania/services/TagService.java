package com.codecooll.RestaurantMania.services;


import com.codecooll.RestaurantMania.data.repository.RestaurantTagRepository;
import com.codecooll.RestaurantMania.data.repository.TagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.model.RestaurantTag;
import com.codecooll.RestaurantMania.restaurant.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final RestaurantService restaurantService;
    private final RestaurantTagRepository restaurantTagRepository;

    public Tag addTagInRestaurant(Long restaurant_id, Long tag_id){
        Restaurant restaurant = Restaurant.builder().id(restaurant_id).restaurantTags(new ArrayList<>()).build();
        Tag tag = Tag.builder().id(tag_id).restaurantTags(new ArrayList<>()).build();

        if(restaurant != null && tag != null){
            RestaurantTag restaurantTag = RestaurantTag.builder().restaurant(restaurant).tag(tag).build();
            restaurantTagRepository.save(restaurantTag);
        }
        return tag;
    }

    public List<Tag> getSomeTags(int pageNr, int quantity){
        Pageable pageable = PageRequest.of(pageNr, quantity);
        return tagRepository.getSomeTags(pageable);
    }
}
