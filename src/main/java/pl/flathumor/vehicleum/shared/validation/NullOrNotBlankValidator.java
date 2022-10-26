package pl.flathumor.vehicleum.shared.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    return value == null || !isBlank(value);
  }
}
