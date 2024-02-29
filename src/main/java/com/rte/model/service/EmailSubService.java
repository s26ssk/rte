package com.rte.model.service;

import com.rte.model.entity.EmailSub;

public interface EmailSubService extends IGenericService<EmailSub, Integer> {
    boolean isEmailSubscribed(String email);
}
