package pl.flathumor.vehicleum.vehicle;

import static java.util.Arrays.stream;

public enum VehicleState {
  OPERATE, REPAIR;
  // TODO: Add meaningful exception
  public VehicleState from(final String name) {
    return stream(values())
        .filter(e -> e.name().equals(name))
        .findFirst()
        .orElseThrow();
  }
}
