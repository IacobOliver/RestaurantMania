package com.codecooll.RestaurantMania.restaurant.service.categoryProductService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/categoryProduct")
public class CategoryProductController {
    private CategoryProductService categoryProductService;

    @Autowired
    public CategoryProductController(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "post/new/category/{menu_id}")
    public ResponseEntity<CategoryProduct> postNewCategory(@PathVariable Long menu_id, @RequestBody CategoryProduct categoryProduct) {

        return ResponseEntity.ok( categoryProductService.addNewProductCategory(menu_id, categoryProduct));
    }

    @CrossOrigin(origins = "*")
    @PatchMapping(path = "update/name/{category_Id}")
    public void updateCategoryName(@PathVariable Long category_Id, @RequestBody String newCategoryName){
        categoryProductService.updateCategoryName( category_Id,  newCategoryName);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "delete/{categ_id}")
    public ResponseEntity<String> deleteCategById(@PathVariable Long categ_id){
        categoryProductService.deleteCategById(categ_id);

        return ResponseEntity.ok("Deleted categ " +  categ_id);
    }

}
