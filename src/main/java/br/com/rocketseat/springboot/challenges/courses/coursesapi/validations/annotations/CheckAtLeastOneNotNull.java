package br.com.rocketseat.springboot.challenges.courses.coursesapi.validations.annotations;

import br.com.rocketseat.springboot.challenges.courses.coursesapi.exceptions.AllFieldsNullException;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.RECORD_COMPONENT;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({RECORD_COMPONENT, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckAtLeastOneNotNull.CheckAtLeastOneNotNullValidator.class)
@Documented
public @interface CheckAtLeastOneNotNull {

  String message() default "At least one field must be provided";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] fieldNames();

  @SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
  public static class CheckAtLeastOneNotNullValidator implements ConstraintValidator<CheckAtLeastOneNotNull, Object> {

    private String[] fieldNames;

    public void initialize(CheckAtLeastOneNotNull constraintAnnotation) {
      this.fieldNames = constraintAnnotation.fieldNames();
    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

      if (object == null) {
        return true;
      }

      try {
        for (String fieldName : fieldNames) {
          Class<?> clazz = object.getClass();
          Field field = clazz.getDeclaredField(fieldName);

          field.setAccessible(true);
          Object fieldValue = field.get(object);

          if (fieldValue != null) return true;

          field.setAccessible(false);
        }

        throw new AllFieldsNullException(constraintContext.getDefaultConstraintMessageTemplate(), fieldNames);
      } catch (Exception e) {
        throw new AllFieldsNullException(constraintContext.getDefaultConstraintMessageTemplate(), fieldNames);
      }
    }
  }
}