package com.codecooll.RestaurantMania.restaurant.model;

import com.codecooll.RestaurantMania.restaurant.model.Account;
import com.codecooll.RestaurantMania.restaurant.model.Rating;
import com.codecooll.RestaurantMania.restaurant.model.Restaurant;
import com.codecooll.RestaurantMania.restaurant.types.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Restaurant> restaurants = new ArrayList<>();

    private int accountFee = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rating> ratings = new ArrayList<>();

    public void addNewRestaurant(Restaurant newRestaurant) {
        this.restaurants.add(newRestaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
        restaurant.setUser(null);
    }

    @Builder
    public User(String firstName, String lastName, String email, String password, Role role, Long id) {
        super(id,firstName, lastName, email, password, role);
    }

    public User(Long id, String firstName, String lastName, String email, String password, Role role) {
        super(id, firstName, lastName, email, password, role);
    }

    public User(Long id, String firstName, String lastName, String email, Role role) {
        super(id, firstName, lastName, email, role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

//    @Override
//    public String getPassword() {
//        return password;
//    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
