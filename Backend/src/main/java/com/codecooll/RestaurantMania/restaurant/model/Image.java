package com.codecooll.RestaurantMania.restaurant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String imageUrl;

}
