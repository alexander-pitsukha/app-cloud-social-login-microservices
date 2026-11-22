package com.alexander.services.identity.controller;

import com.alexander.services.identity.config.AppConstants;
import com.alexander.services.identity.config.CurrentUser;
import com.alexander.services.identity.dto.*;
import com.alexander.services.identity.entity.User;
import com.alexander.services.identity.exception.UserAlreadyExistAuthenticationException;
import com.alexander.services.identity.security.jwt.TokenProvider;
import com.alexander.services.identity.service.MailService;
import com.alexander.services.identity.service.UserService;
import com.alexander.services.identity.util.GeneralUtils;
import com.alexander.services.library.util.ApiResponse;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final QrDataFactory qrDataFactory;
    private final QrGenerator qrGenerator;
    private final CodeVerifier verifier;
    private final MailService mailService;

    @PostMapping("signin")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody @Validated LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LocalUser localUser = (LocalUser) authentication.getPrincipal();
        boolean authenticated = !localUser.getUser().isUsing2FA();
        String jwt = tokenProvider.createToken(localUser, authenticated);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, authenticated, authenticated ? GeneralUtils.buildUserInfo(localUser) : null));
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated SignUpRequest signUpRequest) {
        try {
            User user = userService.registerNewUser(signUpRequest);
            final String token = UUID.randomUUID().toString();
            userService.createVerificationTokenForUser(user, token);
            mailService.sendVerificationToken(token, user);
            if (signUpRequest.isUsing2FA()) {
                QrData data = qrDataFactory.newBuilder().label(user.getEmail()).secret(user.getSecret()).issuer("Java").build();
                String qrCodeImage = getDataUriForImage(qrGenerator.generate(data), qrGenerator.getImageMimeType());
                return ResponseEntity.ok().body(new SignUpResponse(true, qrCodeImage));
            }
        } catch (UserAlreadyExistAuthenticationException e) {
            log.error("Exception Occurred", e);
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        } catch (QrGenerationException e) {
            log.error("QR Generation Exception Occurred", e);
            return new ResponseEntity<>(new ApiResponse(false, "Unable to generate QR code!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("verify")
    @PreAuthorize("hasRole('PRE_VERIFICATION_USER')")
    public ResponseEntity<Object> verifyCode(@RequestBody @NotEmpty String code, @CurrentUser LocalUser user) {
        if (!verifier.isValidCode(user.getUser().getSecret(), code)) {
            return new ResponseEntity<>(new ApiResponse(false, "Invalid Code!"), HttpStatus.BAD_REQUEST);
        }
        String jwt = tokenProvider.createToken(user, true);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, true, GeneralUtils.buildUserInfo(user)));
    }

    @PostMapping("token/verify")
    public ResponseEntity<ApiResponse> confirmRegistration(@RequestBody @NotEmpty String token) {
        final String result = userService.validateVerificationToken(token);
        return ResponseEntity.ok().body(new ApiResponse(true, result));
    }

    @PostMapping("token/resend")
    public ResponseEntity<ApiResponse> resendRegistrationToken(@RequestBody @NotEmpty String expiredToken) {
        if (!userService.resendVerificationToken(expiredToken)) {
            return new ResponseEntity<>(new ApiResponse(false, "Token not found!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

}
