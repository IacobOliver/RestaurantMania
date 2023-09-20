package com.codecooll.RestaurantMania;

import com.codecooll.RestaurantMania.restaurant.model.User;
import com.codecooll.RestaurantMania.services.AccountService;
import com.codecooll.RestaurantMania.restaurant.model.*;
import com.codecooll.RestaurantMania.services.CategoryProductService;
import com.codecooll.RestaurantMania.services.ProductService;
import com.codecooll.RestaurantMania.services.RestaurantService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Populate {
    private RestaurantService restaurantService;
    private AccountService accountService;
    private CategoryProductService categoryProductService;
    private ProductService productService;

    public Populate(RestaurantService restaurantService, AccountService accountService, CategoryProductService categoryProductService, ProductService productService) {
        this.restaurantService = restaurantService;
        this.accountService = accountService;
        this.categoryProductService = categoryProductService;
        this.productService = productService;
    }

    public void populate() {
        String jsonPath = "C:\\Users\\valib\\OneDrive\\Desktop\\MyProjects\\RestaurantMania\\Backend\\src\\main\\resources\\restaurant.json";
        Long adminId = createAdmin("Valentin", "Vali", "valentin@gmail.com", "parolaparola");

        saveRestaurantsFromJsonFile(jsonPath, adminId);
    }

    private Long createAdmin(String firstName, String lastName, String email, String password) {
        accountService.addNewUser(User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build());
        return accountService.getAccountByEmail(email).getId();
    }

    public void saveRestaurantsFromJsonFile(String jsonPath, Long adminId) {

        JSONParser jsonParserObj = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParserObj.parse(new FileReader(jsonPath));
            JSONArray jsonArrObj = (JSONArray) jsonObj.get("Restaurants");
            Iterator<JSONObject> iteratorObj = jsonArrObj.iterator();

            while (iteratorObj.hasNext()) {
                JSONObject restaurantJson = iteratorObj.next();
                Restaurant restaurant = restaurantFromJsonObject(restaurantJson);

                Long menuId = restaurantService.addNewRestaurant(adminId, restaurant).getMenu().getId();
                JSONArray menuJSON = (JSONArray) restaurantJson.get("menu");
                menuFromJsonObject(menuJSON, menuId);

            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    public Restaurant restaurantFromJsonObject(JSONObject json) {
        String name = (String) json.get("name");
        String address = (String) json.get("address");
        String description = (String) json.get("description");
        Image image = imageFromJsonObject((JSONObject) json.get("image"));
        return Restaurant.builder()
                .name(name)
                .address(address)
                .description(description)
                .image(image).active(true)
                .build();
    }

    public Image imageFromJsonObject(JSONObject json) {
        if (json != null) {
            return Image.builder().imageUrl((String) json.get("imageUrl")).build();
        }
        return null;
    }

    public void menuFromJsonObject(JSONArray menuJson, Long menuId) {
        Iterator<JSONObject> iteratorObj = menuJson.iterator();

        while (iteratorObj.hasNext()) {
            JSONObject categoryProductJson = iteratorObj.next();
            categoryProductFromJsonObject(categoryProductJson, menuId);
        }
    }


    public void categoryProductFromJsonObject(JSONObject categoryProductJson, Long menuId) {
        String name = (String) categoryProductJson.get("name");
        Long categoryId = categoryProductService.addNewProductCategory(menuId, CategoryProduct.builder().name(name).build()).getId();

        JSONArray productsJson = (JSONArray) categoryProductJson.get("products");
        Iterator<JSONObject> iteratorObj = productsJson.iterator();

        while (iteratorObj.hasNext()) {
            JSONObject productJson = iteratorObj.next();

            productFromJsonObject(productJson, categoryId);
        }
    }

    public void productFromJsonObject(JSONObject productJson, Long categoryId) {
        String name = (String) productJson.get("name");
        double price = 0.0;
        Object value = productJson.get("price");
        Image image = imageFromJsonObject((JSONObject) productJson.get("image"));

        if (value instanceof Long) {
            price = ((Long) value).doubleValue();
        } else if (value instanceof Double) {
            price = (Double) value;
        }
        String description = (String) productJson.get("productDescription");

        productService.addNewProduct(categoryId, Product.builder().name(name).price(price).productDescription(description).image(image).build());
    }

}

