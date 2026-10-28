package com.alexander.dto;

import java.util.List;

public record UserInfo(String id, String displayName, String email, List<String> roles) {

}
