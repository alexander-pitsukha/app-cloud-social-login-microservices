package com.alexander.services.identity.service;

public interface MessageService {

    String getMessage(String code);

    String getMessage(String code, Object... params);

}
