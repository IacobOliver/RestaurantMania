package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.service.amazon.AwsS3Service;
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
@CrossOrigin(origins = {"http://localhost:5174/", "http://192.168.42.192:5174", "*"})
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final AwsS3Service awsS3Service;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, AwsS3Service awsS3Service) {
        this.restaurantService = restaurantService;
        this.awsS3Service = awsS3Service;
    }

    @PostMapping(path = "/addRestaurant/{client_id}")
    public ResponseEntity<Restaurant> addRestaurant(@PathVariable Long client_id, @RequestBody Restaurant newRestaurant) {
        Restaurant createdRestaurant = restaurantService.addNewRestaurant(client_id, newRestaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @GetMapping(path = "getById/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantByID(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getById(restaurantId));
    }

    @PostMapping(path = "/uploadProfileImage/{restaurantId}")
    public ResponseEntity<Image> uploadProfileImage(@PathVariable Long restaurantId, @RequestBody MultipartFile image) {
        try {
            Image imageUrl = awsS3Service.uploadImage(image);
            restaurantService.setRestaurantImageUrl(restaurantId, imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping(path = "/update/name/{restaurantId}")
    public void updateName(@PathVariable Long restaurantId,@RequestBody String newName){
        System.out.println(newName);
        restaurantService.updateRestaurantName(restaurantId,newName);
    }
    @PatchMapping(path = "/update/description/{restaurantId}")
    public void updateDescription(@PathVariable Long restaurantId,@RequestBody String newDescription){
        System.out.println(newDescription);
        restaurantService.updateRestaurantDescription(restaurantId,newDescription);
    }

    @PatchMapping(path = "/activate/{restaurant_id}")
    public void setActive(@PathVariable Long restaurant_id, @RequestBody Boolean value){
        restaurantService.setActive(restaurant_id, value);
    }

    @DeleteMapping(path = "/delete/{restaurantId}")
    public void deleteRestaurantById(@PathVariable Long restaurantId) {
        restaurantService.deleteById(restaurantId);
    }
}
