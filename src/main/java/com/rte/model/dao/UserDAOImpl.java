package com.rte.model.dao;


import com.rte.model.entity.User;
import com.rte.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllUsers()}")) {
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setRole(resultSet.getByte("role"));
                        user.setUserStatus(resultSet.getBoolean("user_status"));
                        userList.add(user);
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

        return userList;
    }

    @Override
    public Boolean create(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL AddUser(?, ?, ?, ?, ?)}")) {
                callableStatement.setString(1, user.getUserName());
                callableStatement.setString(2, user.getEmail());
                callableStatement.setString(3, user.getPassword());
                callableStatement.setByte(4, user.getRole());
                callableStatement.setBoolean(5, user.getUserStatus());
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
    public Boolean update(User user, Integer userId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL UpdateUser(?, ?, ?, ?, ?, ?)}")) {
                callableStatement.setInt(1, userId);
                callableStatement.setString(2, user.getUserName());
                callableStatement.setString(3, user.getEmail());
                callableStatement.setString(4, user.getPassword());
                callableStatement.setByte(5, user.getRole());
                callableStatement.setBoolean(6, user.getUserStatus());
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
    public User findId(Integer userId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetUserById(?)}")) {
                callableStatement.setInt(1, userId);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setRole(resultSet.getByte("role"));
                        user.setUserStatus(resultSet.getBoolean("user_status"));
                        return user;
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
    public void delete(Integer userId) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL DeleteUser(?)}")) {
                callableStatement.setInt(1, userId);
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
    public User findByEmail(String email) {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetUserByEmail(?)}")) {
                callableStatement.setString(1, email);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setRole(resultSet.getByte("role"));
                        user.setUserStatus(resultSet.getBoolean("user_status"));
                        return user;
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
    public int countRegisteredAccounts() {
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL CountRegisteredAccounts()}")) {
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
    public List<User> findByUserNameContaining(String userName) {
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetUserByUserNameContaining(?)}")) {
                callableStatement.setString(1, userName);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setRole(resultSet.getByte("role"));
                        user.setUserStatus(resultSet.getBoolean("user_status"));
                        userList.add(user);
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

        return userList;
    }
    @Override
    public List<User> findAllPage(int offset, int size) {
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            try (CallableStatement callableStatement = connection.prepareCall("{CALL GetAllUsersPaged(?, ?)}")) {
                callableStatement.setInt(1, offset);
                callableStatement.setInt(2, size);
                try (ResultSet resultSet = callableStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setRole(resultSet.getByte("role"));
                        user.setUserStatus(resultSet.getBoolean("user_status"));
                        userList.add(user);
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

        return userList;
    }
}
