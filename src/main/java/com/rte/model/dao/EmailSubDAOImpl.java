package com.rte.model.dao;

import com.rte.model.entity.EmailSub;
import com.rte.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmailSubDAOImpl implements EmailSubDAO{
    @Override
    public List<EmailSub> findAll() {
        Connection connection = null;
        List<EmailSub> emailSubList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllEmailSubs()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        EmailSub emailSub = new EmailSub();
                        emailSub.setIdEmailSub(resultSet.getInt("idEmailSub"));
                        emailSub.setEmailSub(resultSet.getString("emailSub"));
                        emailSub.setTimeSub(resultSet.getTimestamp("timeSub"));
                        emailSubList.add(emailSub);
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

        return emailSubList;
    }
    @Override
    public Boolean create(EmailSub emailSub) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddEmailSub(?, ?)}")) {
                callableStatement.setString(1, emailSub.getEmailSub());
                callableStatement.setTimestamp(2, emailSub.getTimeSub());
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
    public Boolean update(EmailSub emailSub, Integer integer) {
        return null;
    }

    @Override
    public EmailSub findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
    @Override
    public boolean isEmailSubscribed(String email) {
        Connection connection = null;

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL IsEmailSubscribed(?, ?)}")) {
                callableStatement.setString(1, email);
                callableStatement.registerOutParameter(2, Types.BOOLEAN);
                callableStatement.execute();

                return callableStatement.getBoolean(2);
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
}
