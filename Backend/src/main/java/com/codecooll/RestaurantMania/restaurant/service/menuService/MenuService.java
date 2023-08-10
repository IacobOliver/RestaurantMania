package com.codecooll.RestaurantMania.restaurant.service.menuService;

import com.codecooll.RestaurantMania.restaurant.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    @Transactional
    @Modifying
    public Menu deleteById(Long menuId){
        Menu menu = menuRepository.getById(menuId);
        System.out.println("RIP MENU " + menu);
         menuRepository.deleteById(menuId);
         menuRepository.delete(menu);
         return menu;
    }
}
