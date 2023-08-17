package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.accounts.service.AccountRepository;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.service.cloudStorage.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AccountRepository accountRepository;
    private final ImageService imageService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, AccountRepository accountRepository, ImageService imageService) {
        this.restaurantRepository = restaurantRepository;
        this.accountRepository = accountRepository;
        this.imageService = imageService;
    }

    public Restaurant addNewRestaurant(Long client_id, Restaurant newRestaurant) {
        User user = (User) accountRepository.findById(client_id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        newRestaurant.setUser(user);

        Menu defaultMenu = new Menu();
        newRestaurant.setMenu(defaultMenu);

        restaurantRepository.save(newRestaurant);

        return newRestaurant;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(long restaurantID) {
        Restaurant res = restaurantRepository.getById(restaurantID);
        return res;
    }

    @Transactional
    public void deleteById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        User user = restaurant.getUser();

        user.removeRestaurant(restaurant);

        restaurantRepository.deleteById(restaurantId);
    }

    public void setRestaurantImageUrl(Long restaurantId, Image image) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        if (restaurant.getImage() != null) {
            String url = restaurant.getImage().getImageUrl();
            imageService.deleteImageByUrl(url);
        }

        restaurant.setImage(image);
        restaurantRepository.save(restaurant);
    }

    public void updateRestaurantName(Long restaurantId, String newName) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        restaurant.setName(newName);
        restaurantRepository.save(restaurant);
    }

    public void updateRestaurantDescription(Long restaurantId, String newDescription) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        restaurant.setDescription(newDescription);
        restaurantRepository.save(restaurant);
    }

    public void setActive(Long restaurant_id, Boolean value) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);
        restaurant.setActive(value);
        restaurantRepository.save(restaurant);
    }

}
