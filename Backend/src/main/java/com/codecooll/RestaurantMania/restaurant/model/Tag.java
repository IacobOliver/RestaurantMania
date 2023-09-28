package com.codecooll.RestaurantMania.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    public void addRestaurant(Restaurant restaurant) {
        if(this.restaurants == null){
            this.restaurants = new ArrayList<>();
        }
        this.restaurants.add(restaurant);
    }
}
