package com.rte.model.dao;

import com.rte.model.entity.Contact;

import java.util.List;

import com.rte.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
@Repository
public class ContactDAOImpl implements ContactDAO{
    @Override
    public List<Contact> findAll() {
        Connection connection = null;
        List<Contact> contactList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllContacts()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Contact contact = new Contact();
                        contact.setContactId(resultSet.getInt("contactId"));
                        contact.setName(resultSet.getString("name"));
                        contact.setEmail(resultSet.getString("email"));
                        contact.setOrderId(resultSet.getString("orderId"));
                        contact.setMessage(resultSet.getString("message"));
                        contact.setTimeContact(resultSet.getTimestamp("timeContact"));
                        contactList.add(contact);
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

        return contactList;
    }

    @Override
    public Boolean create(Contact contact) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddContact(?, ?, ?, ?, ?)}")) {
                callableStatement.setString(1, contact.getName());
                callableStatement.setString(2, contact.getEmail());
                callableStatement.setString(3,contact.getOrderId());
                callableStatement.setString(4, contact.getMessage());
                callableStatement.setTimestamp(5, contact.getTimeContact());

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
    public Boolean update(Contact contact, Integer integer) {
        return null;
    }

    @Override
    public Contact findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
