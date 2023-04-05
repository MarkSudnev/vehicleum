package pl.flathumor.vehicleum.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {
  Class<?> entityClass();
  String message() default "Must exists";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
