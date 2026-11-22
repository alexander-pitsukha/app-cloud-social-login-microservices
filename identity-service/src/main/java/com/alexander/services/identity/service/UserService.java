package com.alexander.services.identity.service;

import com.alexander.services.identity.dto.LocalUser;
import com.alexander.services.identity.dto.SignUpRequest;
import com.alexander.services.identity.entity.User;
import com.alexander.services.identity.exception.UserAlreadyExistAuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;

public interface UserService {

    User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User findUserByEmail(String email);

    User findUserById(Long id);

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

    void createVerificationTokenForUser(User user, String token);

    boolean resendVerificationToken(String token);

    String validateVerificationToken(String token);

}
