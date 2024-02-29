package com.rte.model.dao;

import com.rte.dto.request.ReqProductDTO;
import com.rte.model.entity.Category;
import com.rte.model.entity.Product;
import com.rte.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllProducts()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = mapResultSetToProduct(resultSet);
                        productList.add(product);
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

        return productList;
    }

    @Override
    public Boolean create(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddProduct(?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

                callableStatement.setString(1, product.getProductName());
                callableStatement.setInt(2, product.getCategory().getCategoryId());
                callableStatement.setString(3, product.getImage1());
                callableStatement.setString(4, product.getImage2());
                callableStatement.setDouble(5, product.getImport_price());
                callableStatement.setDouble(6, product.getExport_price());
                callableStatement.setString(7, product.getDescription());
                callableStatement.setInt(8, product.getStock());
                callableStatement.setBoolean(9, product.getStatus());

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
    public Boolean update(Product product, Integer productId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL UpdateProduct(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
                callableStatement.setInt(1, productId);
                setProductParameters(callableStatement, product);
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
    public Product findId(Integer productId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetProductById(?)}")) {
                callableStatement.setInt(1, productId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToProduct(resultSet);
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
    public void delete(Integer productId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL DeleteProduct(?)}")) {
                callableStatement.setInt(1, productId);
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

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("productId"));
        product.setProductName(resultSet.getString("productName"));
        product.setCategory(categoryDAO.findId(resultSet.getInt("categoryId")));
        product.setImage1(resultSet.getString("image1"));
        product.setImage2(resultSet.getString("image2"));
        product.setImport_price(resultSet.getDouble("import_price"));
        product.setExport_price(resultSet.getDouble("export_price"));
        product.setDescription(resultSet.getString("description"));
        product.setStock(resultSet.getInt("stock"));
        product.setStatus(resultSet.getBoolean("status"));
        return product;
    }

    private void setProductParameters(CallableStatement callableStatement, Product product) throws SQLException {
        callableStatement.setString(2, product.getProductName());
        callableStatement.setInt(3, product.getCategory().getCategoryId());
        callableStatement.setString(4, product.getImage1());
        callableStatement.setString(5, product.getImage2());
        callableStatement.setDouble(6, product.getImport_price());
        callableStatement.setDouble(7, product.getExport_price());
        callableStatement.setString(8, product.getDescription());
        callableStatement.setInt(9, product.getStock());
        callableStatement.setBoolean(10, product.getStatus());
    }

    @Override
    public List<Product> findByProductName(String productName) {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetProductByProductName(?)}")) {
                callableStatement.setString(1, productName);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = mapResultSetToProduct(resultSet);
                        productList.add(product);
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

        return productList;
    }

    @Override
    public List<Product> findAllPage(int offset, int size) {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetProductsPaged(?, ?)}")) {
                callableStatement.setInt(1, offset);
                callableStatement.setInt(2, size);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = mapResultSetToProduct(resultSet);
                        productList.add(product);
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

        return productList;
    }

    @Override
    public int countAllProducts() {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL CountAllProducts()}")) {
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

    @Override
    public Boolean updateStock(Integer productId, Integer quantity) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL UpdateStock(?, ?)}")) {
                callableStatement.setInt(1, productId);
                callableStatement.setInt(2, quantity);
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
    public List<Product> findAllPageSortedByExportPrice(int page, int size, String order) {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetProductsPagedSortedByExportPrice(?, ?, ?)}")) {
                callableStatement.setInt(1, page);
                callableStatement.setInt(2, size);
                callableStatement.setString(3, order);

                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = mapResultSetToProduct(resultSet);
                        productList.add(product);
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

        return productList;
    }
}
