package com.rte.model.dao;

import com.rte.model.entity.Cart;

public interface CartDAO {
    Boolean addCart(Cart cart) ;
    Cart findByIdUser( Integer idUser) ;

    Boolean deleteCart(Integer cartId);
    Cart findByIdCart(Integer cartId);

}
