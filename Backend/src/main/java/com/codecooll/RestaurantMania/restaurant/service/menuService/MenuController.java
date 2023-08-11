package com.codecooll.RestaurantMania.restaurant.service.menuService;

import com.codecooll.RestaurantMania.restaurant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/menu")
@CrossOrigin(origins = "http://localhost:5174/")
public class MenuController {
    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @DeleteMapping(path = "/delete/{menuId}")
    public ResponseEntity<Menu> deleteMenuById(@PathVariable Long menuId){
       return ResponseEntity.ok( menuService.deleteById(menuId));
    }

}
