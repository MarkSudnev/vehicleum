package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class AssignationDetailDto {
  UUID id;
  DriverDto driver;
  VehicleDto vehicle;
  LocalDateTime startDate;
  LocalDateTime finishDate;
  String note;

  @Value
  @Builder
  public static class VehicleDto {
    UUID id;
    String plate;
  }

  @Value
  @Builder
  public static class DriverDto {
    UUID id;
    String name;
  }
}
