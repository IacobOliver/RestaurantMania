package com.codecooll.RestaurantMania.services;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.data.repository.CategoryProductRepository;
import com.codecooll.RestaurantMania.data.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    private final MenuRepository menuRepository;
    
    public CategoryProduct addNewProductCategory(long menu_id, CategoryProduct categoryProduct) {

        Menu menu = menuRepository.getById(menu_id);
        categoryProduct.setMenu(menu);
        categoryProductRepository.save(categoryProduct);

        return categoryProduct;
    }

    public void updateCategoryName(Long categoryId, String newCategoryName){
       categoryProductRepository.updateNameById(categoryId,newCategoryName);
    }

    @Transactional
    @Modifying
    public void deleteCategById(Long categ_id){
        CategoryProduct categoryProduct = categoryProductRepository.findById(categ_id).orElse(null);
        categoryProduct.getMenu().removeCategoryProduct(categoryProduct);
        categoryProductRepository.deleteById(categ_id);

    }
}
