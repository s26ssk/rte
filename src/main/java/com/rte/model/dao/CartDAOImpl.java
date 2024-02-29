package com.rte.model.dao;

import com.rte.util.ConnectionDB;
import com.rte.model.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CartDAOImpl implements CartDAO{
    @Autowired
    private UserDAOImpl userDAO;
    @Override
    public Boolean addCart(Cart cart) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddCart(?)}")) {
                callableStatement.setInt(1, cart.getUser().getUserId());
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
    public Cart findByIdUser(Integer idUser) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCartByUserId(?)}")) {
                callableStatement.setInt(1, idUser);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Cart cart = new Cart();
                        cart.setCart_id(resultSet.getInt("cart_id"));
                        cart.setUser(userDAO.findId(resultSet.getInt("user_id")));
                        return cart;
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
    public Boolean deleteCart(Integer cartId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL DeleteCart(?)}")) {
                callableStatement.setInt(1, cartId);
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
    public Cart findByIdCart(Integer cartId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetCartById(?)}")) {
                callableStatement.setInt(1, cartId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Cart cart = new Cart();
                        cart.setCart_id(resultSet.getInt("cart_id"));
                        cart.setUser(userDAO.findId(resultSet.getInt("user_id")));
                        return cart;
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


}
