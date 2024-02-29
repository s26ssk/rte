package com.rte.model.entity;

public class ShippingPrice {
    private Integer id;
    private String countryName;
    private Double priceOne;
    private Double priceThree;

    public ShippingPrice() {
    }

    public ShippingPrice(Integer id, String countryName, Double priceOne, Double priceThree) {
        this.id = id;
        this.countryName = countryName;
        this.priceOne = priceOne;
        this.priceThree = priceThree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Double priceOne) {
        this.priceOne = priceOne;
    }

    public Double getPriceThree() {
        return priceThree;
    }

    public void setPriceThree(Double priceThree) {
        this.priceThree = priceThree;
    }
}
