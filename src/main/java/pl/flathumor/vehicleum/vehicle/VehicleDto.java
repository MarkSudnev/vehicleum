package pl.flathumor.vehicleum.vehicle;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class VehicleDto {

  UUID id;
  String brand;
  String model;
  Integer year;
  String plate;
  VehicleState vehicleState;
}
