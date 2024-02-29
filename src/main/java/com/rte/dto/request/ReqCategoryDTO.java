package com.rte.dto.request;

import javax.validation.constraints.NotEmpty;

public class ReqCategoryDTO {
    @NotEmpty(message = "Category name cannot be empty")
    private String categoryName;
    private Boolean status;

    public ReqCategoryDTO() {
    }

    public ReqCategoryDTO(String categoryName, Boolean status) {
        this.categoryName = categoryName;
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
