package com.alexander.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {

    String id;

    String displayName;

    String email;

    List<String> roles;

}
