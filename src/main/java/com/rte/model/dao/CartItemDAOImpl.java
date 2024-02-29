package com.rte.model.dao;

import com.rte.model.entity.Cart;
import com.rte.model.entity.CartItem;
import com.rte.model.entity.Product;
import com.rte.model.entity.User;
import com.rte.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CartItemDAOImpl implements CartItemDAO{
    @Autowired
    private ProductDAOImpl productDAO;
    @Autowired
    private CartDAOImpl cartDAO;
    @Autowired
    private HttpSession httpSession;

    @Override
    public List<CartItem> findAll() {
        Connection connection = null;
        List<CartItem> cartItemList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllCartItems()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        CartItem cartItem = new CartItem();
                        cartItem.setId(resultSet.getInt("id"));
                        User userLogin = (User) httpSession.getAttribute("loggedInUser");
                        cartItem.setCart(cartDAO.findByIdUser(resultSet.getInt(userLogin.getUserId())));
                        cartItem.setProduct(productDAO.findId((resultSet.getInt("productId"))));
                        cartItem.setQuantity(resultSet.getInt("quantity"));
                        cartItemList.add(cartItem);
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

        return cartItemList;
    }

    @Override
    public Boolean create(CartItem cartItem) {
        Connection connection = null;
        System.out.println(cartItem.getCart().getCart_id());
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddCartItem(?, ?, ?)}")) {
                callableStatement.setInt(1, cartItem.getCart().getCart_id());
                callableStatement.setInt(2, cartItem.getProduct().getProductId());
                callableStatement.setInt(3, cartItem.getQuantity());
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
    public Boolean update(CartItem cartItem, Integer cartItemId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL UpdateCartItem(?, ?, ?)}")) {
                callableStatement.setInt(1, cartItemId);
                callableStatement.setInt(2, cartItem.getQuantity());
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
    public CartItem findId(Integer cartItemId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCartItemById(?)}")) {
                callableStatement.setInt(1, cartItemId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        CartItem cartItem = new CartItem();
                        cartItem.setId(resultSet.getInt("cart_item_id"));
                        User userLogin = (User) httpSession.getAttribute("loggedInUser");
                        cartItem.setCart(cartDAO.findByIdUser(resultSet.getInt(userLogin.getUserId())));
                        cartItem.setProduct(productDAO.findId(resultSet.getInt("product_id")));
                        cartItem.setQuantity(resultSet.getInt("quantity"));
                        return cartItem;
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
    public void delete(Integer cartItemId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL DeleteCartItem(?)}")) {
                callableStatement.setInt(1, cartItemId);
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
    public List<CartItem> findAllByCartId(Integer cartId) {
        Connection connection = null;
        List<CartItem> cartItemList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllCartItemsByCartId(?)}")) {
                callableStatement.setInt(1, cartId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        CartItem cartItem = new CartItem();
                        cartItem.setId(resultSet.getInt("cart_item_id"));
                        User userLogin = (User) httpSession.getAttribute("loggedInUser");
                        cartItem.setCart(cartDAO.findByIdUser(userLogin.getUserId()));
                        cartItem.setProduct(productDAO.findId(resultSet.getInt("product_id")));
                        cartItem.setQuantity(resultSet.getInt("quantity"));
                        cartItemList.add(cartItem);
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

        return cartItemList;
    }
}
