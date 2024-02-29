package com.rte.dto.request;

import com.rte.model.entity.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReqProductDTO {
    private Integer productId;
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;
    private Category category;
//    @NotNull(message = "Please upload two photos")
    private String image1;
//    @NotNull(message = "Please upload two photos")
    private String image2;
    @NotNull(message = "Import price cannot be empty")
    private Double import_price;
    @NotNull(message = "Export price cannot be empty")
    private Double export_price;
    @NotNull(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Stock cannot be empty")
    private Integer stock;
    private Boolean status;

    public ReqProductDTO() {
    }

    public ReqProductDTO(Integer productId, String productName, Category category, String image1, String image2, Double import_price, Double export_price, String description, Integer stock, Boolean status) {
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
