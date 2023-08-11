package com.codecooll.RestaurantMania.restaurant.service.productService;

import com.codecooll.RestaurantMania.restaurant.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/product")
@CrossOrigin(origins = "http://localhost:5174/")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "post/new/product/{categ_id}")
    public ResponseEntity<Product> postNewProduct(@PathVariable Long categ_id, @RequestBody Product product) {

        return ResponseEntity.ok( productService.addNewProduct(categ_id, product)) ;
    }

}
