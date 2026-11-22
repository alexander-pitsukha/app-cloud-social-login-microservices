package com.alexander.services.identity.dto;

import com.alexander.services.identity.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Collection;

@Getter
@Builder
public class LocalUserBuilder {

    private String userId;

    private String password;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private Collection<? extends GrantedAuthority> authorities;

    private User user;

    private OidcIdToken idToken;

    private OidcUserInfo userInfo;

}
