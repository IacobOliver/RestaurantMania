package com.codecooll.RestaurantMania;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class Populate {

    public static void main(String[] args) {
        JSONParser jsonParserObj = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) jsonParserObj.parse(new FileReader("C:\\Users\\valib\\OneDrive\\Desktop\\MyProjects\\RestaurantMania\\Backend\\src\\main\\resources\\restaurant.json"));
            JSONArray jsonArrObj = (JSONArray) jsonObj.get("Restaurants");
            Iterator<JSONObject> iteratorObj = jsonArrObj.iterator();

            while (iteratorObj.hasNext()) {
                JSONObject restaurantJson = iteratorObj.next();
                Restaurant restaurant = restaurantFromJsonObject(restaurantJson);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static Restaurant restaurantFromJsonObject(JSONObject json){
        String name = (String) json.get("name");
        String address =(String) json.get("adress");
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


    public static CategoryProduct categoryProductFromJsonObject(JSONObject categoryProductJson){
        String name = (String) categoryProductJson.get("name");
        JSONArray productsJson = (JSONArray) categoryProductJson.get("products");

        List<Product> productList = new ArrayList<>();
        Iterator<JSONObject> iteratorObj = productsJson.iterator();

        while (iteratorObj.hasNext()){
            Product product = productFromJsonObject(iteratorObj.next());
            productList.add(product);
        }
        return CategoryProduct.builder().name(name).products(productList).build();
    }

    public static Product productFromJsonObject(JSONObject productJson){


        return null;
    }

}

