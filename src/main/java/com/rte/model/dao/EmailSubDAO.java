package com.rte.model.dao;

import com.rte.model.entity.EmailSub;

public interface EmailSubDAO extends IGenericDAO<EmailSub, Integer>{
    boolean isEmailSubscribed(String email);
}
