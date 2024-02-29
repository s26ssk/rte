package com.rte.model.entity;

import java.sql.Timestamp;

public class Contact {
    private Integer contactId;
    private String name;
    private String email;
    private String orderId;
    private String message;
    private Timestamp timeContact;

    public Contact() {
        this.timeContact = new Timestamp(System.currentTimeMillis());
    }

    public Contact(Integer contactId, String name, String email,String orderId, String message,Timestamp timeContact) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
        this.orderId = orderId;
        this.message = message;
        this.timeContact = timeContact;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Timestamp getTimeContact() {
        return (Timestamp) timeContact;
    }

    public void setTimeContact(java.util.Date timeSub) {
        this.timeContact = new Timestamp(timeSub.getTime());
    }
}
