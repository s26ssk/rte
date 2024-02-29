package com.rte.dto.response;

public class RespUserLoginDTO {
    private Integer userId;
    private String userName;
    private String email;
    private Byte role;
    private Boolean userStatus;

    public RespUserLoginDTO() {
    }

    public RespUserLoginDTO(Integer userId, String userName, String email, Byte role, Boolean userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }
}
