package com.rte.model.service;

import com.rte.model.entity.Cart;

public interface CartService {
    Boolean addCart(Cart cart) ;
    Cart findByIdUser( Integer idUser) ;
    Boolean deleteCart(Integer cartId);

}
