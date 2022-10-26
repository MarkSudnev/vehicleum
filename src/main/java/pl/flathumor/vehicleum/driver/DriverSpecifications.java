package pl.flathumor.vehicleum.driver;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.fieldLike;

@UtilityClass
public class DriverSpecifications {

  public static Specification<DriverEntity> nameLike(final String keyword) {
    return fieldLike("name", keyword);
  }

  public static Specification<DriverEntity> phoneLike(final String keyword) {
    return fieldLike("phone", keyword);
  }

  public static Specification<DriverEntity> emailLike(final String keyword) {
    return fieldLike("email", keyword);
  }
}
