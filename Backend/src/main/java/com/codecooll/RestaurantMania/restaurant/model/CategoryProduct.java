package com.codecooll.RestaurantMania.restaurant.model;

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
@Table(name = "categoryProducts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "CategoryProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
