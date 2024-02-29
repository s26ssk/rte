package com.rte.model.service;

import com.rte.model.entity.CartItem;

import java.util.List;

public interface CartItemService extends IGenericService<CartItem, Integer>{
    List<CartItem> findAllByIdCart(Integer cartId);

}
