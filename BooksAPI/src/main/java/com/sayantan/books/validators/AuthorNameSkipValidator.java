package com.sayantan.books.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorNameValidator.class)
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorNameSkipValidator {
    String message() default "Invalid author name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
