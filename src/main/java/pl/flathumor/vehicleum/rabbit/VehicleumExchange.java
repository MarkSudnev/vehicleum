package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VehicleumExchange {

  VEHICLE("vehicle-exchange"),
  DRIVER("driver-exchange"),
  ASSIGNATION("assignation-exchange");

  private final String name;
}
