package com.rte.model.dao;

import com.rte.model.entity.User;

import java.util.List;

public interface UserDAO extends IGenericDAO<User, Integer>{
    User findByEmail(String email);
    int countRegisteredAccounts();
    List<User> findAllPage(int offset, int size);

    List<User> findByUserNameContaining(String userName);
}
