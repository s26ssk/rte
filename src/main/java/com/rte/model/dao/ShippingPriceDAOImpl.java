package com.rte.model.dao;

import com.rte.model.entity.ShippingPrice;
import com.rte.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ShippingPriceDAOImpl implements ShippingPriceDAO{
    @Override
    public List<ShippingPrice> findAll() {
        Connection connection = null;
        List<ShippingPrice> shippingPriceList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllShippingPrices()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ShippingPrice shippingPrice = new ShippingPrice();
                        shippingPrice.setId(resultSet.getInt("id"));
                        shippingPrice.setCountryName(resultSet.getString("country_name"));
                        shippingPrice.setPriceOne(resultSet.getDouble("price_one"));
                        shippingPrice.setPriceThree(resultSet.getDouble("price_three"));
                        shippingPriceList.add(shippingPrice);
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

        return shippingPriceList;
    }
    @Override
    public Boolean create(ShippingPrice shippingPrice) {
        return null;
    }

    @Override
    public Boolean update(ShippingPrice shippingPrice, Integer integer) {
        return null;
    }

    @Override
    public ShippingPrice findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
