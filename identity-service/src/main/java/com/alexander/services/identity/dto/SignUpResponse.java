package com.alexander.services.identity.dto;

public record SignUpResponse(boolean using2FA, String qrCodeImage) {

}
