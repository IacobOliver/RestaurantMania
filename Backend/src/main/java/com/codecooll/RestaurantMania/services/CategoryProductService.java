package com.codecooll.RestaurantMania.services;

import com.codecooll.RestaurantMania.restaurant.model.CategoryProduct;
import com.codecooll.RestaurantMania.restaurant.model.Menu;
import com.codecooll.RestaurantMania.data.repository.CategoryProductRepository;
import com.codecooll.RestaurantMania.data.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryProductService {

    private final CategoryProductRepository categoryProductRepository;
    
    public CategoryProduct addNewProductCategory(long menu_id) {

        Menu menu = Menu.builder().id(menu_id).build();
        CategoryProduct categoryProduct = CategoryProduct.builder().menu(menu).build();


        categoryProductRepository.save(categoryProduct);

        return categoryProduct;
    }

    public void updateCategoryName(Long categoryId, String newCategoryName){
       categoryProductRepository.updateNameById(categoryId,newCategoryName);
    }

    @Transactional
    @Modifying
    public void deleteCategById(Long categ_id){
        categoryProductRepository.deleteById(categ_id);

    }

    public List<CategoryProduct> getSomeOfMenu(long menu_id, int pageNr,int itemsPerPage){
        Pageable pageable = PageRequest.of(pageNr,itemsPerPage);

        return categoryProductRepository.getSomeOfMenu(menu_id,pageable);
    }

}
