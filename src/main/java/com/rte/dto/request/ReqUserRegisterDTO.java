package com.rte.dto.request;

import com.rte.dto.validate.UniqueEmail;

import javax.validation.constraints.*;

public class ReqUserRegisterDTO {
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+=]).*$",
            message = "Password must start with an uppercase letter, contain at least one digit, and one special character")
    private String password;

    @NotEmpty(message = "Confirm Password cannot be empty")
    private String confirmPassword;

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }


    public ReqUserRegisterDTO() {

    }

    public ReqUserRegisterDTO(String userName, String email, String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
