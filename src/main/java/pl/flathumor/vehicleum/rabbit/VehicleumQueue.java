package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;

@Getter
public enum VehicleumQueue {

  VEHICLE_REPARATION("vehicle-reparation", RoutingKey.VEHICLE_REPARATION),
  DRIVER_DUTY("driver-duty", RoutingKey.DRIVER_DUTY),
  ASSIGNATIONS("assignations", RoutingKey.ASSIGNATION);

  private final String name;
  private final String deadLetterQueue;
  private final String deadLetterRoutingKey;
  private final RoutingKey routingKey;

  VehicleumQueue(final String name, final RoutingKey routingKey) {
    this.name = name;
    this.routingKey = routingKey;
    this.deadLetterQueue = "x-dead-letter-" + name;
    this.deadLetterRoutingKey = "x-dead-letter-" + routingKey.getKey();
  }
}
