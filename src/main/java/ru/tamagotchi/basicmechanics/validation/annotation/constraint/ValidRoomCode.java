package ru.tamagotchi.basicmechanics.validation.annotation.constraint;

import ru.tamagotchi.basicmechanics.validation.validatior.RoomCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by makar
 * 18.12.2018 0:29
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoomCodeValidator.class)
@SuppressWarnings("unused")
public @interface ValidRoomCode {
    String message() default "{value.room.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
