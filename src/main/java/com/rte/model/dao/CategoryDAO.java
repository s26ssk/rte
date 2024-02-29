package com.rte.model.dao;

import com.rte.model.entity.Category;

import java.util.List;

public interface CategoryDAO extends IGenericDAO<Category, Integer> {
    List<Category> findByCategoryName(String categoryName);
    List<Category> findAllPage(int page, int size);
    int countAllCategories();
}
