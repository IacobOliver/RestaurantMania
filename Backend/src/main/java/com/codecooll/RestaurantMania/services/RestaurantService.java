package com.codecooll.RestaurantMania.services;

import com.codecooll.RestaurantMania.data.repository.*;
import com.codecooll.RestaurantMania.restaurant.model.User;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.services.cloudStorage.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AccountRepository accountRepository;
    private final ImageService imageService;
    private final CategoryProductRepository categoryProductRepository;
    private final MenuRepository menuRepository;
    private final ImageUrlRepository imageUrlRepository;


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
        Restaurant res = restaurantRepository.findByIdWithoutMenu(restaurantID).orElse(null);
        System.out.println(res.getId());
        Menu menu = menuRepository.getByRestaurantIdWithoutCategoryProducts(restaurantID).orElse(null);

//        Pageable pageable = PageRequest.of(0, 5);
//        menu.setCategoryProducts(categoryProductRepository.getSomeOfMenu(menu.getId(), pageable));
        res.setMenu(menu);
        return res;
    }

    public List<Restaurant> getWithoutMenu() {
        List<Restaurant> res = restaurantRepository.findAllRestaurantsWithNamesAndRatings();
        return res;
    }

    @Transactional
    @Modifying
    public void deleteById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);

        User user = restaurant.getUser();

        user.removeRestaurant(restaurant);

        restaurantRepository.deleteById(restaurantId);
    }

    public void setRestaurantImageUrl(Long restaurantId, Image image) {
        Restaurant restaurant = restaurantRepository.findByIdWithoutMenu(restaurantId).orElse(null);
        if (restaurant.getImage() != null && restaurant.getImage().getImageUrl() != null) {
            String url = restaurant.getImage().getImageUrl();
            imageService.deleteImageByUrl(url);
        }
            restaurantRepository.updateImageById(restaurantId,image);
    }

    public void updateRestaurantName(Long restaurantId, String newName) {
        restaurantRepository.updateNameById(restaurantId, newName);
    }

    public void updateRestaurantDescription(Long restaurantId, String newDescription) {
        restaurantRepository.updateDescriptionById(restaurantId, newDescription);
    }

    public void setActive(Long restaurant_id, Boolean value) {
        restaurantRepository.updateIsActiveById(restaurant_id, value);

    }

}
