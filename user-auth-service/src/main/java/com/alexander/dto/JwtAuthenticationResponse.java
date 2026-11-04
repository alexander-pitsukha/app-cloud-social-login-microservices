package com.alexander.dto;

public record JwtAuthenticationResponse(String accessToken, boolean authenticated, UserInfo user) {

}
