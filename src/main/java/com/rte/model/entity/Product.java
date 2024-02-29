package com.rte.model.entity;

public class Product {
    private Integer productId;
    private String productName;
    private Category category;
    private String image1;
    private String image2;
    private Double import_price;
    private Double export_price;
    private String description;
    private Integer stock;
    private Boolean status;

    public Product() {
    }

    public Product(Integer productId, String productName, Category category, String image1, String image2, Double import_price, Double export_price, String description, Integer stock, Boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.image1 = image1;
        this.image2 = image2;
        this.import_price = import_price;
        this.export_price = export_price;
        this.description = description;
        this.stock = stock;
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public Double getImport_price() {
        return import_price;
    }

    public void setImport_price(Double import_price) {
        this.import_price = import_price;
    }

    public Double getExport_price() {
        return export_price;
    }

    public void setExport_price(Double export_price) {
        this.export_price = export_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
