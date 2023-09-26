package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.services.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/categoryProduct")
@RequiredArgsConstructor
public class CategoryProductController {
    private final CategoryProductService categoryProductService;


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

    @GetMapping(path = "getSome/{menuId}/{pageNr}/{itemsPerPage}")
    public ResponseEntity<List<CategoryProduct>> getSomeOfMenu(@PathVariable Long menuId,@PathVariable int pageNr,@PathVariable int itemsPerPage){
        return ResponseEntity.ok(categoryProductService.getSomeOfMenu(menuId,pageNr,itemsPerPage));
    }


}
