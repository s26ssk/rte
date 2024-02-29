package com.rte.model.service;

import com.rte.model.dao.ContactDAOImpl;
import com.rte.model.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactDAOImpl contactDAO;
    @Override
    public List<Contact> findAll() {
        return contactDAO.findAll();
    }

    @Override
    public Boolean create(Contact contact) {
        return contactDAO.create(contact);
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
