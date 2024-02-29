package com.rte.model.service;

import com.rte.model.dao.CartDAOImpl;
import com.rte.model.dao.CartItemDAOImpl;
import com.rte.model.dao.ProductDAOImpl;
import com.rte.model.entity.Cart;
import com.rte.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDAOImpl cartDAO;
    @Autowired
    private CartItemDAOImpl cartItemDAO;
    @Autowired
    private ProductDAOImpl productDAO;
    @Override
    public Boolean addCart(Cart cart) {
        return cartDAO.addCart(cart);
    }

    @Override
    public Cart findByIdUser(Integer idUser) {
        return cartDAO.findByIdUser(idUser);
    }


    @Override
    public Boolean deleteCart(Integer cartId) {
        Cart cart = cartDAO.findByIdCart(cartId);
        if (cart != null) {
            List<CartItem> cartItems = cartItemDAO.findAllByCartId(cartId);
            for (CartItem cartItem : cartItems) {
                productDAO.updateStock(cartItem.getProduct().getProductId(), cartItem.getQuantity());
            }
            if (cartDAO.deleteCart(cartId)) {
                return true;
            }
        }
        return false;
    }

}
