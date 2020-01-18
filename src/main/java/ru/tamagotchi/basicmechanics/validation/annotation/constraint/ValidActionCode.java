package ru.tamagotchi.basicmechanics.validation.annotation.constraint;

import ru.tamagotchi.basicmechanics.validation.validatior.ActionCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by makar
 * 17.10.2018 21:29
 *
 * <p>JSR-303 annotation</p>
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ActionCodeValidator.class)
@SuppressWarnings("unused")
public @interface ValidActionCode {
    String message() default "{value.actionCode.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
