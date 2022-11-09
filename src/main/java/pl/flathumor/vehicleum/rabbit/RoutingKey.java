package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoutingKey {

  VEHICLE_REPARATION("vehicle.reparation"),
  VEHICLE_STATE("vehicle.state"),
  DRIVER_DUTY("driver.duty"),
  ASSIGNATION("drier.assignation");

  private final String key;
}
