package com.alexander.services.identity.service;

import com.alexander.services.identity.entity.User;

public interface MailService {

    void sendVerificationToken(String token, User user);

}
