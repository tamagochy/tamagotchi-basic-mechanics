package ru.tamagotchi.basicmechanics.validation.validatior;

import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.validation.annotation.constraint.ValidActionCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by makar
 * 17.10.2018 21:35
 */
public class ActionCodeValidator implements ConstraintValidator<ValidActionCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            ActionCode.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
