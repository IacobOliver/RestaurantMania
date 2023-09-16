package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.service.cloudStorage.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ImageService imageService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService, ImageService imageService) {
        this.restaurantService = restaurantService;
        this.imageService = imageService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addRestaurant/{client_id}")
    public ResponseEntity<Restaurant> addRestaurant(@PathVariable Long client_id, @RequestBody Restaurant newRestaurant) {
        Restaurant createdRestaurant = restaurantService.addNewRestaurant(client_id, newRestaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getAll/withoutMenu")
    public ResponseEntity<List<Restaurant>> getRestaurantWithoutMenu() {
        return ResponseEntity.ok(restaurantService.getWithoutMenu());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getById/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantByID(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getById(restaurantId));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/uploadProfileImage/{restaurantId}")
    public ResponseEntity<Image> uploadProfileImage(@PathVariable Long restaurantId, @RequestBody MultipartFile image) {
        try {
            Image imageObj = imageService.uploadImage(image);
            restaurantService.setRestaurantImageUrl(restaurantId, imageObj);
            return ResponseEntity.ok(imageObj);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PatchMapping(path = "/update/name/{restaurantId}")
    public void updateName(@PathVariable Long restaurantId, @RequestBody String newName) {
        System.out.println(newName);
        restaurantService.updateRestaurantName(restaurantId, newName);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping(path = "/update/description/{restaurantId}")
    public void updateDescription(@PathVariable Long restaurantId, @RequestBody String newDescription) {
        System.out.println(newDescription);
        restaurantService.updateRestaurantDescription(restaurantId, newDescription);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping(path = "/activate/{restaurant_id}")
    public void setActive(@PathVariable Long restaurant_id, @RequestBody Boolean value) {
        restaurantService.setActive(restaurant_id, value);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/delete/{restaurantId}")
    public void deleteRestaurantById(@PathVariable Long restaurantId) {
        restaurantService.deleteById(restaurantId);
    }




}