package com.codecooll.RestaurantMania.restaurant.service.productService;

import com.codecooll.RestaurantMania.restaurant.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
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

    @PatchMapping(path = "update/name/{product_id}")
    public void updateProductName(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "name");
    }

    @PatchMapping(path = "update/productDescription/{product_id}")
    public void updateProductDescription(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "description");
    }

    @PatchMapping(path = "update/price/{product_id}")
    public void updateProductPrice(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "price");
    }

}
