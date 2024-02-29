package com.rte.model.service;

import com.rte.model.dao.CategoryDAOImpl;
import com.rte.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDAOImpl categoryDAO;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Boolean update(Category category, Integer categoryId) {
        return categoryDAO.update(category, categoryId);
    }

    @Override
    public Category findId(Integer categoryId) {
        return categoryDAO.findId(categoryId);
    }

    @Override
    public void delete(Integer categoryId) {
        categoryDAO.delete(categoryId);
    }

    @Override
    public List<Category> findByCategoryName(String categoryName) {
        return categoryDAO.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findAllPage(int page, int size) {
        return categoryDAO.findAllPage(page, size);
    }

    @Override
    public int countAllCategories() {
        return categoryDAO.countAllCategories();
    }
}
