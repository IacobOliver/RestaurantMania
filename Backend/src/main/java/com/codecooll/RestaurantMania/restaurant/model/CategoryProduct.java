package com.codecooll.RestaurantMania.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoryProducts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;


    @OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    @Override
    public String toString() {
        return "CategoryProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    public void removeProduct( Product product){
        products.remove(product);
        product.setCategoryProduct(null);
    }
}
