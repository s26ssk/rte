package com.rte.model.service;

import com.rte.model.dao.UserDAOImpl;
import com.rte.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Boolean create(User user) {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setRole((byte) 0);
        user.setUserStatus(true);

        return userDAO.create(user);
    }

    @Override
    public Boolean update(User user, Integer userId) {
        return userDAO.update(user, userId);
    }

    @Override
    public User findId(Integer userId) {
        return userDAO.findId(userId);
    }

    @Override
    public void delete(Integer userId) {
        userDAO.delete(userId);
    }

    @Override
    public boolean registerUser(User user) {
        if (emailExists(user.getEmail())) {
            System.out.println(user.getUserName());
            return false;
        }
        user.setRole((byte) 0);
        user.setUserStatus(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.create(user);
    }

    @Override
    public User findByEmail(String email) {
       return userDAO.findByEmail(email);
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }
    @Override
    public boolean emailExists(String email) {
        return userDAO.findByEmail(email) != null;
    }

    @Override
    public void blockUser(Integer userId) {
        User user = userDAO.findId(userId);
        if (user != null) {
            user.setUserStatus(false);
            userDAO.update(user, userId);
        }
    }

    @Override
    public void unblockUser(Integer userId) {
        User user = userDAO.findId(userId);
        if (user != null) {
            user.setUserStatus(true);
            userDAO.update(user, userId);
        }
    }
    public int countRegisteredAccounts() {
        return userDAO.countRegisteredAccounts();
    }


    @Override
    public List<User> findAllPage(int page, int size) {
        int offset = page * size;
        return userDAO.findAllPage(offset, size);
    }

    @Override
    public List<User> findByUserNameContaining(String userName) {
        return userDAO.findByUserNameContaining(userName);
    }


}
