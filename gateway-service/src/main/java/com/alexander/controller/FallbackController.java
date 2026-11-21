package com.alexander.controller;

import com.alexander.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @PostMapping("identity-service-fallback")
    public ResponseEntity<ApiResponse> authFallback() {
        return new ResponseEntity<>(new ApiResponse(false, "Identity Service is down! Please try later"), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
