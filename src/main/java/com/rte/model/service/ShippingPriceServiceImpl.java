package com.rte.model.service;

import com.rte.model.dao.ShippingPriceDAOImpl;
import com.rte.model.entity.ShippingPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShippingPriceServiceImpl implements ShippingPriceService{
    @Autowired
    private ShippingPriceDAOImpl shippingPriceDAO;
    @Override
    public List<ShippingPrice> findAll() {
        return shippingPriceDAO.findAll();
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
