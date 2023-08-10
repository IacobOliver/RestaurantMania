package com.codecooll.RestaurantMania.restaurant.model;

import com.codecooll.RestaurantMania.accounts.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String address;
    private double rating = 0;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<RestaurantRating> restaurantRatings = new ArrayList<>();

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                '}';
    }
}
