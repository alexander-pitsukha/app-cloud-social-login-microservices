package com.alexander.services.identity.validator;

import com.alexander.services.identity.dto.SignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpRequest> {

    @Override
    public boolean isValid(SignUpRequest user, ConstraintValidatorContext context) {
        return Objects.equals(user.getPassword(), user.getMatchingPassword());
    }

}
