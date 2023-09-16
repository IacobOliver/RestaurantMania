package com.codecooll.RestaurantMania.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "productRatings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Proxy(lazy = false)
public class ProductRating extends Rating {

//    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Image> images;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
