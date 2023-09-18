package com.codecooll.RestaurantMania.restaurant.service.restaurantService;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.accounts.service.AccountRepository;
import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.service.categoryProductService.CategoryProductRepository;
import com.codecooll.RestaurantMania.restaurant.service.cloudStorage.ImageService;
import com.codecooll.RestaurantMania.restaurant.service.menuService.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        System.out.println("menu ++++++++++++++++++++++++++++++++++++++++++++++=");
        Menu menu = menuRepository.getByRestaurantIdWithoutCategoryProducts(restaurantID).orElse(null);
        System.out.println("first ++++++++++++++++++++++++++ " + menu.getCategoryProducts());
        Pageable pageable = PageRequest.of(0, 5);

        menu.setCategoryProducts(categoryProductRepository.getSomeOfMenu(menu.getId(), pageable));
        System.out.println("second ++++++++++++++++++++++++++ " + menu.getCategoryProducts().size());
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
