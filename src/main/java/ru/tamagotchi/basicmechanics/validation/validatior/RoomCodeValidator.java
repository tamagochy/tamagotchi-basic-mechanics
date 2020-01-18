package ru.tamagotchi.basicmechanics.validation.validatior;

import ru.tamagotchi.basicmechanics.domain.RoomCode;
import ru.tamagotchi.basicmechanics.validation.annotation.constraint.ValidRoomCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by makar
 * 18.12.2018 0:31
 */
public class RoomCodeValidator implements ConstraintValidator<ValidRoomCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            RoomCode.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
