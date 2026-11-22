package com.alexander.services.identity.dto;

public record AuthenticationResponse(String accessToken, boolean authenticated, UserInfo user) {
}
