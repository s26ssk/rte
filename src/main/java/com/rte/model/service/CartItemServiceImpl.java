package com.rte.model.service;

import com.rte.model.dao.CartDAOImpl;
import com.rte.model.dao.CartItemDAOImpl;
import com.rte.model.entity.Cart;
import com.rte.model.entity.CartItem;
import com.rte.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemDAOImpl cartItemDAO;
    @Autowired
    private CartDAOImpl cartDAO;
    @Autowired
    private HttpSession session;
    @Override
    public List<CartItem> findAll() {
        return cartItemDAO.findAll();
    }

    @Override
    public Boolean create(CartItem cartItem) {
        return cartItemDAO.create(cartItem);
    }

    @Override
    public Boolean update(CartItem cartItem, Integer id) {
        return cartItemDAO.update(cartItem, id);
    }

    @Override
    public CartItem findId(Integer id) {
        return cartItemDAO.findId(id);
    }

    @Override
    public void delete(Integer id) {
        cartItemDAO.delete(id);
    }

    @Override
    public List<CartItem> findAllByIdCart(Integer cartId) {
        User userLogin = (User) session.getAttribute("loggedInUser");
        Cart cart = cartDAO.findByIdUser(userLogin.getUserId());
        return cartItemDAO.findAllByCartId(cart.getCart_id());
    }
}
