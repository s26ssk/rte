package com.rte.model.dao;

import com.rte.model.entity.CartItem;

import java.util.List;

public interface CartItemDAO extends IGenericDAO<CartItem, Integer> {
    List<CartItem> findAllByCartId(Integer cartId);
}
