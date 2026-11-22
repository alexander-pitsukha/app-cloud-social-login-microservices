package com.alexander.services.identity.dto;

import com.alexander.services.identity.validator.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignUpRequest {

    private Long userId;

    private String providerUserId;

    @NotEmpty
    private String displayName;

    @Email
    private String email;

    private SocialProvider socialProvider;

    @Size(min = 6, message = "{size.userDto.password}")
    private String password;

    @NotEmpty
    private String matchingPassword;

    private boolean using2FA;

    public SignUpRequest(String providerUserId, String displayName, String email, String password, String matchingPassword, SocialProvider socialProvider) {
        this.providerUserId = providerUserId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.socialProvider = socialProvider;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String providerUserID;
        private String displayName;
        private String email;
        private String password;
        private String matchingPassword;
        private SocialProvider socialProvider;

        public Builder addProviderUserID(String userId) {
            this.providerUserID = userId;
            return this;
        }

        public Builder addDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder addEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder addPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder addMatchingPassword(String matchingPassword) {
            this.matchingPassword = matchingPassword;
            return this;
        }

        public Builder addSocialProvider(SocialProvider socialProvider) {
            this.socialProvider = socialProvider;
            return this;
        }

        public SignUpRequest build() {
            return new SignUpRequest(providerUserID, displayName, email, password, matchingPassword, socialProvider);
        }

    }

}
