package com.rte.model.service;

import com.rte.model.entity.Category;

import java.util.List;

public interface CategoryService extends IGenericService<Category,Integer> {
    List<Category> findByCategoryName(String categoryName);
    List<Category> findAllPage(int page, int size);
    int countAllCategories();
}
