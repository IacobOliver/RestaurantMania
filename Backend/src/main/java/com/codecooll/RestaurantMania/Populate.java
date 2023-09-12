package com.codecooll.RestaurantMania;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.codecooll.RestaurantMania.accounts.service.AccountService;
import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.service.restaurantService.RestaurantService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class Populate {

    @Autowired
    private static AccountService accountService;
    @Autowired
    private static RestaurantService restaurantService;



    public static void main(String[] args) {
        String jsonPath = "C:\\Users\\valib\\OneDrive\\Desktop\\MyProjects\\RestaurantMania\\Backend\\src\\main\\resources\\restaurant.json";
        List<Restaurant> restaurantList = getRestaurantsFromJsonFile(jsonPath);
        Long adminId = createAdmin("Valentin", "Vali", "papuci@slapi.com", "kebab");

        for (Restaurant restaurant : restaurantList) {
            Long restaurantId = restaurantService.addNewRestaurant(adminId,restaurant).getId();
        }

    }

    private static Long createAdmin(String firstName, String lastName, String email, String password) {
        accountService.addNewUser(User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build());
        return accountService.getAccountByEmail(email).getId();
    }

    public static List<Restaurant> getRestaurantsFromJsonFile(String jsonPath) {
        List<Restaurant> restaurantList = new ArrayList<>();
        JSONParser jsonParserObj = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParserObj.parse(new FileReader(jsonPath));
            JSONArray jsonArrObj = (JSONArray) jsonObj.get("Restaurants");
            Iterator<JSONObject> iteratorObj = jsonArrObj.iterator();

            while (iteratorObj.hasNext()) {
                JSONObject restaurantJson = iteratorObj.next();
                Restaurant restaurant = restaurantFromJsonObject(restaurantJson);
                restaurantList.add(restaurant);
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return restaurantList;
    }

    public static Restaurant restaurantFromJsonObject(JSONObject json) {
        String name = (String) json.get("name");
        String address = (String) json.get("adress");
        String description = (String) json.get("description");
        JSONArray menuJSON = (JSONArray) json.get("menu"); // Extract the "menu" object

        // Create a Menu object from the "menu" JSON
        Menu menu = menuFromJsonObject(menuJSON);

        return Restaurant.builder()
                .name(name)
                .address(address)
                .description(description)
                .menu(menu)
                .build();
    }

    public static Menu menuFromJsonObject(JSONArray menuJson) {
        Iterator<JSONObject> iteratorObj = menuJson.iterator();
        List<CategoryProduct> categoryProductList = new ArrayList<>();

        while (iteratorObj.hasNext()) {
            JSONObject categoryProductJson = iteratorObj.next();
            CategoryProduct categoryProduct = categoryProductFromJsonObject(categoryProductJson);
            categoryProductList.add(categoryProduct);
        }
        return Menu.builder().categoryProducts(categoryProductList).build();
    }


    public static CategoryProduct categoryProductFromJsonObject(JSONObject categoryProductJson) {
        String name = (String) categoryProductJson.get("name");
        JSONArray productsJson = (JSONArray) categoryProductJson.get("products");

        List<Product> productList = new ArrayList<>();
        Iterator<JSONObject> iteratorObj = productsJson.iterator();

        while (iteratorObj.hasNext()) {
            Product product = productFromJsonObject(iteratorObj.next());
            productList.add(product);
        }
        return CategoryProduct.builder().name(name).products(productList).build();
    }

    public static Product productFromJsonObject(JSONObject productJson) {
        String name = (String) productJson.get("name");
        double price = (double) productJson.get("price");
        String description = (String) productJson.get("productDescription");

        return Product.builder().name(name).price(price).productDescription(description).build();
    }

}

