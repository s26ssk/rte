package com.rte.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ReqUserLoginDTO {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String userName;



    public ReqUserLoginDTO() {
    }

    public ReqUserLoginDTO(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
