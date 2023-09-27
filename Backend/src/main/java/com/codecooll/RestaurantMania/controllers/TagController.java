package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.data.repository.TagRepository;
import com.codecooll.RestaurantMania.restaurant.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/restaurantTag")
public class TagController {
    private final TagRepository tagRepository;

    @PostMapping("/saveTags")
    @CrossOrigin("*")
    public void saveTags(@RequestBody List<String> tags) {
        List<Tag> response = tags.stream().map(item -> Tag.builder().name(item).build()).toList();
        tagRepository.saveAll(response);
    }

    @GetMapping("/getAll")
    @CrossOrigin("*")
    public ResponseEntity<List<Tag>> getTags() {
        return ResponseEntity.ok(tagRepository.findAll());
    }
}
