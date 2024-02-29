package com.rte.model.entity;

public class Cart {
    private static int nextCartId = 1;
    private Integer cart_id ;
    private User user ;

    public Cart() {
        this.cart_id = nextCartId++;
    }

    public Cart(User user) {
        this.cart_id = nextCartId++;
        this.user = user;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
