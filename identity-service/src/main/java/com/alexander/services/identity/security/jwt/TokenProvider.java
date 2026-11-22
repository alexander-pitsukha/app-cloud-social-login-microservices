package com.alexander.services.identity.security.jwt;

import com.alexander.services.identity.config.AppConstants;
import com.alexander.services.identity.config.AppProperties;
import com.alexander.services.identity.dto.LocalUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import static com.alexander.services.library.util.AppConstants.ROLES;

@Service
@Slf4j
public class TokenProvider {

    private final AppProperties appProperties;
    private final Key key;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(appProperties.getAuth().getTokenSecret()));
    }

    public String createToken(LocalUser userPrincipal, boolean authenticated) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()
                + (authenticated ? appProperties.getAuth().getTokenExpirationMsec() : AppConstants.TEMP_TOKEN_VALIDITY_IN_MILLIS));
        String roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder().setSubject(Long.toString(userPrincipal.getUser().getId())).claim(ROLES, roles)
                .claim(AppConstants.AUTHENTICATED, authenticated).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(key)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = parseClaims(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public Boolean isAuthenticated(String token) {
        Claims claims = parseClaims(token).getBody();
        return claims.get(AppConstants.AUTHENTICATED, Boolean.class);
    }

    public boolean validateToken(String authToken) {
        try {
            parseClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.", e);
        }
        return false;
    }

}
