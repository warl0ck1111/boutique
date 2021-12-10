package com.aneeque.backendservice.util.validator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Okala Bashir .O.
 * created at 06/02/2015
 */

@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ConfirmPassword {

    String passwordField() default "password";

    String confirmPasswordField() default "passwordConfirm";

    String message() default "{ConfirmPassword.message}";

    Class[] groups() default {};

    Class[] payload() default {};

}
