package com.codecooll.RestaurantMania.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
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
}
