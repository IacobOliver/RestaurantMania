package com.codecooll.RestaurantMania.restaurant.service.categoryProductService;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.restaurant.service.menuService.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public CategoryProductService(CategoryProductRepository categoryProductRepository, MenuRepository menuRepository) {
        this.categoryProductRepository = categoryProductRepository;
        this.menuRepository = menuRepository;
    }

    public CategoryProduct addNewProductCategory(Long menu_id, CategoryProduct categoryProduct) {

        Menu menu = menuRepository.getById(menu_id);
        categoryProduct.setMenu(menu);
        categoryProductRepository.save(categoryProduct);

        return categoryProduct;
    }

    public void updateCategoryName(Long categoryId, String newCategoryName){
        CategoryProduct categoryProduct = categoryProductRepository.findById(categoryId).orElse(null);
        categoryProduct.setName(newCategoryName);
        categoryProductRepository.save(categoryProduct);
    }

    @Transactional
    @Modifying
    public void deleteCategById(Long categ_id){
        CategoryProduct categoryProduct = categoryProductRepository.findById(categ_id).orElse(null);
        categoryProduct.getMenu().removeCategoryProduct(categoryProduct);
        categoryProductRepository.deleteById(categ_id);

    }
}
