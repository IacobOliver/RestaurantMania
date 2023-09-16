package com.codecooll.RestaurantMania.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Proxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menues")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Proxy(lazy = false)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lastUpdated = LocalDate.now().toString();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<CategoryProduct> categoryProducts = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL,orphanRemoval = true)
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", categoryProducts=" + categoryProducts +
                ", lastUpdated=" + lastUpdated +
                '}';
    }

    public void removeCategoryProduct(CategoryProduct categoryProduct){
        categoryProducts.remove(categoryProduct);
        categoryProduct.setMenu(null);
    }
}
