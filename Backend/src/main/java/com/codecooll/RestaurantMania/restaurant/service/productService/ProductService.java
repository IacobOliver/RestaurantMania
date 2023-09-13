package com.codecooll.RestaurantMania.restaurant.service.productService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.model.Product;
import com.codecooll.RestaurantMania.restaurant.service.categoryProductService.CategoryProductRepository;
import com.codecooll.RestaurantMania.restaurant.service.cloudStorage.ImageService;
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
        Optional<CategoryProduct> categoryProductOptional = categoryProductRepository.findById(categ_id);

        if (categoryProductOptional.isPresent()) {
            product.setCategoryProduct(categoryProductOptional.get());
            CategoryProduct categoryProduct = categoryProductOptional.get();
            categoryProduct.getProducts().add(product);
            productRepository.save(product);
            categoryProductRepository.save(categoryProduct);
        }

        return product;
    }

    public void updateProduct(Long product_id, String value, String key) {
        Product product = productRepository.findById(product_id).orElse(null);
        if (key == "name") product.setName(value);
        else if (key == "description") product.setProductDescription(value);
        else if (key == "price") product.setPrice(Integer.parseInt(value.replaceAll("[^\\d]", "")));
        productRepository.save(product);
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
        Product product = productRepository.findById(product_id).orElse(null);
        product.getCategoryProduct().removeProduct(product);
        productRepository.deleteById(product_id);

    }
}
