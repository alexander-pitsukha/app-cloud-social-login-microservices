package com.alexander.services.identity.dto;

import java.util.List;

public record UserInfo(String id, String displayName, String email, List<String> roles) {

}
