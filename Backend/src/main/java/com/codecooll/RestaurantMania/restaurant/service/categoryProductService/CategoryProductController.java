package com.codecooll.RestaurantMania.restaurant.service.categoryProductService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/categoryProduct")
@CrossOrigin(origins = "http://localhost:5174/")
public class CategoryProductController {
    private CategoryProductService categoryProductService;

    @Autowired
    public CategoryProductController(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @PostMapping(path = "post/new/category/{menu_id}")
    public ResponseEntity<CategoryProduct> postNewCategory(@PathVariable Long menu_id, @RequestBody CategoryProduct categoryProduct) {

        return ResponseEntity.ok( categoryProductService.addNewProductCategory(menu_id, categoryProduct));

    }

}
