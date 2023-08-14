package com.codecooll.RestaurantMania.restaurant.service.productService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.restaurant.service.categoryProductService.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryProductRepository categoryProductRepository) {
        this.categoryProductRepository = categoryProductRepository;
        this.productRepository = productRepository;
    }

    public Product addNewProduct(Long categ_id, Product product) {
        Optional<CategoryProduct> categoryProductOptional = categoryProductRepository.findById(categ_id);

        if (categoryProductOptional.isPresent()) {
            CategoryProduct categoryProduct = categoryProductOptional.get();
            product.setCategoryProduct(categoryProduct);
            categoryProduct.getProducts().add(product);
            productRepository.save(product);
            categoryProductRepository.save(categoryProduct);
        }

        return product;
    }

    public void updateProduct(Long product_id, String value, String key){
        Product product = productRepository.findById(product_id).orElse(null);
        if(key == "name") product.setName(value);
        else if( key == "description") product.setProductDescription(value);
        else if( key == "price") product.setPrice(Integer.parseInt( value.replaceAll("[^\\d]", "")));
        productRepository.save(product);

    }
}
