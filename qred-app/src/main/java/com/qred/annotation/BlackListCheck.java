package com.qred.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BlackListValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BlackListCheck {

    String message() default "registration number black listed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}