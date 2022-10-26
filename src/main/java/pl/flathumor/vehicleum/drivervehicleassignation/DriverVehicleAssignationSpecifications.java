package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.relatedFieldIdIs;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.relatedFieldLike;

@UtilityClass
public class DriverVehicleAssignationSpecifications {

  public static Specification<DriverVehicleAssignationEntity> driverIs(final UUID driverId) {
    return relatedFieldIdIs("driver", driverId);
  }

  public static Specification<DriverVehicleAssignationEntity> driverNameLike(final String driverName) {
    return relatedFieldLike("driver", "name", driverName);
  }

  public static Specification<DriverVehicleAssignationEntity> vehicleIs(final UUID vehicleId) {
    return relatedFieldIdIs("vehicle", vehicleId);
  }

  public static Specification<DriverVehicleAssignationEntity> vehiclePlateLike(final String plate) {
    return relatedFieldLike("vehicle", "plate", plate);
  }

  public static Specification<DriverVehicleAssignationEntity> assignationPeriodIs(
      final LocalDateTime startDate,
      final LocalDateTime finishDate
  ) {
    return (root, query, cb) ->
      cb.or(
          cb.between(root.get("startDate"), startDate, finishDate),
          cb.between(root.get("finishDate"), startDate, finishDate));
  }

  public static Specification<DriverVehicleAssignationEntity> onDate(final LocalDateTime date) {
    return (root, query, cb) ->
        cb.and(
            cb.lessThanOrEqualTo(root.get("startDate"), date),
            cb.greaterThan(root.get("finishDate"), date));
  }
}
