package com.rte.model.entity;

import java.sql.Timestamp;
import java.util.Date;

public class EmailSub {
    private Integer idEmailSub;
    private String emailSub;
    private Timestamp timeSub;

    public EmailSub() {
        this.timeSub = new Timestamp(System.currentTimeMillis());
    }

    public EmailSub(Integer idEmailSub, String emailSub, Timestamp timeSub) {
        this.idEmailSub = idEmailSub;
        this.emailSub = emailSub;
        this.timeSub = timeSub;
    }

    public Integer getIdEmailSub() {
        return idEmailSub;
    }

    public void setIdEmailSub(Integer idEmailSub) {
        this.idEmailSub = idEmailSub;
    }

    public String getEmailSub() {
        return emailSub;
    }

    public void setEmailSub(String emailSub) {
        this.emailSub = emailSub;
    }

    public Timestamp getTimeSub() {
        return (Timestamp) timeSub;
    }

    public void setTimeSub(java.util.Date timeSub) {
        this.timeSub = new Timestamp(timeSub.getTime());
    }
}
