package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoutingKey {

  VEHICLE_REPARATION("vehicle.reparation"),
  DRIVER_DUTY("driver.duty"),
  ASSIGNATION("assignation");

  private final String key;
}
