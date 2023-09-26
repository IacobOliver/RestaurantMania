package com.codecooll.RestaurantMania.data.DAO;

import com.codecooll.RestaurantMania.restaurant.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CategProductDAO {

    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();
}
