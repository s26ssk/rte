package com.rte.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ReqContactDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "OrderId cannot be empty")
    private String orderId;
    @NotEmpty(message = "Message cannot be empty")
    private String message;

    public ReqContactDTO() {
    }

    public ReqContactDTO(String name, String email,String orderId,String message) {
        this.name = name;
        this.email = email;
        this.orderId = orderId;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
