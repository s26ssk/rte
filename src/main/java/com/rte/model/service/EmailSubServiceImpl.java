package com.rte.model.service;

import com.rte.model.dao.EmailSubDAOImpl;
import com.rte.model.entity.EmailSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmailSubServiceImpl implements EmailSubService{
    @Autowired
    private EmailSubDAOImpl emailSubDAO;
    @Override
    public List<EmailSub> findAll() {
        return emailSubDAO.findAll();
    }

    @Override
    public Boolean create(EmailSub emailSub) {
        return emailSubDAO.create(emailSub);
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
        return emailSubDAO.isEmailSubscribed(email);
    }
}
