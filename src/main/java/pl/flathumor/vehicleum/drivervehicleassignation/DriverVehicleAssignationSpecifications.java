package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

import static pl.flathumor.vehicleum.driver.DriverEntity_.NAME;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationEntity_.DRIVER;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationEntity_.FINISH_DATE;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationEntity_.START_DATE;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationEntity_.VEHICLE;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.relatedFieldIdIs;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.relatedFieldLike;
import static pl.flathumor.vehicleum.vehicle.VehicleEntity_.PLATE;

@UtilityClass
public class DriverVehicleAssignationSpecifications {

  public static Specification<DriverVehicleAssignationEntity> driverIs(final UUID driverId) {
    return relatedFieldIdIs(DRIVER, driverId);
  }

  public static Specification<DriverVehicleAssignationEntity> driverNameLike(final String driverName) {
    return relatedFieldLike(DRIVER, NAME, driverName);
  }

  public static Specification<DriverVehicleAssignationEntity> vehicleIs(final UUID vehicleId) {
    return relatedFieldIdIs(VEHICLE, vehicleId);
  }

  public static Specification<DriverVehicleAssignationEntity> vehiclePlateLike(final String plate) {
    return relatedFieldLike(VEHICLE, PLATE, plate);
  }

  public static Specification<DriverVehicleAssignationEntity> assignationPeriodIs(
      final LocalDateTime startDate,
      final LocalDateTime finishDate
  ) {
    return (root, query, cb) ->
      cb.or(
          cb.between(root.get(START_DATE), startDate, finishDate),
          cb.between(root.get(FINISH_DATE), startDate, finishDate));
  }

  public static Specification<DriverVehicleAssignationEntity> onDate(final LocalDateTime date) {
    return (root, query, cb) ->
        cb.and(
            cb.lessThanOrEqualTo(root.get(START_DATE), date),
            cb.greaterThan(root.get(FINISH_DATE), date));
  }
}
