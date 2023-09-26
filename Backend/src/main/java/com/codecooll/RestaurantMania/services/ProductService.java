package com.codecooll.RestaurantMania.services;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.data.repository.CategoryProductRepository;
import com.codecooll.RestaurantMania.services.cloudStorage.ImageService;
import com.codecooll.RestaurantMania.data.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final ImageService imageService;


    @Autowired
    public ProductService(ProductRepository productRepository, CategoryProductRepository categoryProductRepository, ImageService imageService) {
        this.categoryProductRepository = categoryProductRepository;
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    public Product addNewProduct(Long categ_id, Product product) {
       CategoryProduct categoryProduct = CategoryProduct.builder().id(categ_id).build();

       product.setCategoryProduct(categoryProduct);
       productRepository.save(product);
        return product;
    }

    public void updateProduct(Long product_id, String value, String key) {
        if (key == "name") productRepository.updateName(product_id , value);
        else if (key == "description") productRepository.updateDescription(product_id, value);
        else if (key == "price") productRepository.updatePrice(product_id,Integer.parseInt(value.replaceAll("[^\\d]", "")));
    }

    public void setProductImageUrl(Long product_id, Image image) {
        Product product = productRepository.getById(product_id);
        if (product.getImage() != null) {
            String url = product.getImage().getImageUrl();
            imageService.deleteImageByUrl(url);
        }
        product.setImage(image);
        productRepository.save(product);
    }

    @Transactional
    @Modifying
    public void deleteProductById(Long product_id) {
        //productRepository.deleteProductById(product_id);
        productRepository.deleteById(product_id);
    }
}
