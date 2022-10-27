package pl.flathumor.vehicleum.vehicle;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.fieldIs;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.fieldLike;
import static pl.flathumor.vehicleum.vehicle.VehicleEntity_.PLATE;

@UtilityClass
public class VehicleSpecifications {

  public static Specification<VehicleEntity> plateIs(final String plate) {
    return fieldIs(PLATE, plate);
  }

  public static Specification<VehicleEntity> plateLike(final String plate) {
    return fieldLike(PLATE, plate);
  }
}
