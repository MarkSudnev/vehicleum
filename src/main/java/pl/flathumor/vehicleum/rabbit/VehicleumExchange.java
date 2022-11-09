package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VehicleumExchange {

  VEHICLE("vehicle"),
  DRIVER("driver");

  private final String name;
}
