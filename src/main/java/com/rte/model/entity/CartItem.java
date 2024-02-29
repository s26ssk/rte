package com.rte.model.entity;

public class CartItem {
    private static int nextCartItemId = 1;
    private Cart cart ;
    private Integer id ;
    private Product product;
    private Integer quantity ;

    public CartItem() {
        this.id = nextCartItemId++;
    }

    public CartItem(Cart cart, Product product, Integer quantity) {
        this.cart = cart;
        this.id = nextCartItemId++;
        this.product = product;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalRevenue() {
        if (product != null && quantity != null) {
            return (product.getExport_price() - product.getImport_price()) * quantity;
        } else {
            return 0.0;
        }
    }
}
