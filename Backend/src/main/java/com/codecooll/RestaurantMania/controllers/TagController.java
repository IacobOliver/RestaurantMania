package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.data.repository.RestaurantTagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/restaurantTag")
public class RestaurantTagController {
    private final RestaurantTagRepository restaurantTagRepository;

    @PostMapping("saveTags")
    @CrossOrigin("*")
    public void saveTags(@RequestBody List<String> tags){
        List<Tag> response = tags.stream().map(item -> Tag.builder().name(item).build()).toList();
        response.forEach(i -> System.out.println(i.toString()));
        //restaurantTagRepository.saveAll(tags);
    }
}
