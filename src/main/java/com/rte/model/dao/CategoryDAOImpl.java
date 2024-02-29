package com.rte.model.dao;

import com.rte.model.entity.Category;
import com.rte.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categoryList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllCategories()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Category category = new Category();
                        category.setCategoryId(resultSet.getInt("categoryId"));
                        category.setCategoryName(resultSet.getString("categoryName"));
                        category.setStatus(resultSet.getBoolean("status"));
                        categoryList.add(category);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

    @Override
    public Boolean create(Category category) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddCategory(?, ?)}")) {
                callableStatement.setString(1, category.getCategoryName());
                callableStatement.setBoolean(2, category.getStatus());
                int check = callableStatement.executeUpdate();
                return check > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boolean update(Category category, Integer categoryId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL UpdateCategory(?, ?, ?)}")) {
                callableStatement.setInt(1, categoryId);
                callableStatement.setString(2, category.getCategoryName());
                callableStatement.setBoolean(3, category.getStatus());
                int check = callableStatement.executeUpdate();
                return check > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Category findId(Integer categoryId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCategoryById(?)}")) {
                callableStatement.setInt(1, categoryId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Category category = new Category();
                        category.setCategoryId(resultSet.getInt("categoryId"));
                        category.setCategoryName(resultSet.getString("categoryName"));
                        category.setStatus(resultSet.getBoolean("status"));
                        return category;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void delete(Integer categoryId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL DeleteCategory(?)}")) {
                callableStatement.setInt(1, categoryId);
                callableStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Category> findByCategoryName(String categoryName) {
        Connection connection = null;
        List<Category> categoryList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCategoryByCategoryName(?)}")) {
                callableStatement.setString(1, categoryName);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Category category = new Category();
                        category.setCategoryId(resultSet.getInt("categoryId"));
                        category.setCategoryName(resultSet.getString("categoryName"));
                        category.setStatus(resultSet.getBoolean("status"));
                        categoryList.add(category);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

    @Override
    public List<Category> findAllPage(int offset, int size) {
        Connection connection = null;
        List<Category> categoryList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCategoriesPaged(?, ?)}")) {
                callableStatement.setInt(1, offset);
                callableStatement.setInt(2, size);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Category category = new Category();
                        category.setCategoryId(resultSet.getInt("categoryId"));
                        category.setCategoryName(resultSet.getString("categoryName"));
                        category.setStatus(resultSet.getBoolean("status"));
                        categoryList.add(category);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

    @Override
    public int countAllCategories() {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL CountAllCategories()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
