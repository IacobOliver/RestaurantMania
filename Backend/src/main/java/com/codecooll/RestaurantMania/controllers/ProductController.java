package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.services.cloudStorage.ImageService;
import com.codecooll.RestaurantMania.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;
    private final ImageService imageService;

    @Autowired
    public ProductController( ProductService productService,ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "post/new/product/{categ_id}")
    public ResponseEntity<Product> postNewProduct(@PathVariable Long categ_id, @RequestBody Product product) {
        return ResponseEntity.ok( productService.addNewProduct(categ_id, product)) ;
    }
    @CrossOrigin(origins = "*")
    @PatchMapping(path = "update/name/{product_id}")
    public void updateProductName(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "name");
    }
    @CrossOrigin(origins = "*")
    @PatchMapping(path = "update/productDescription/{product_id}")
    public void updateProductDescription(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "description");
    }
    @CrossOrigin(origins = "*")
    @PatchMapping(path = "update/price/{product_id}")
    public void updateProductPrice(@PathVariable Long product_id, @RequestBody String value){
        productService.updateProduct(product_id, value, "price");
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "update/image/{product_id}")
    public ResponseEntity<Image> updateProductImage(@PathVariable Long product_id, @RequestBody MultipartFile image){
        try {
            Image imageObj = imageService.uploadImage(image);
            productService.setProductImageUrl(product_id, imageObj);
            return ResponseEntity.ok(imageObj);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "delete/{product_id}")
    public void deleteProduct(@PathVariable Long product_id){
        productService.deleteProductById(product_id);
    }

}
