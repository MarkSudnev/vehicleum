package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;

import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableSet;

@Getter
public enum VehicleumQueue {

  VEHICLE_REPARATION("vehicle-reparation", VehicleumExchange.VEHICLE, RoutingKey.VEHICLE_REPARATION),
  VEHICLE_STATE("vehicle-state", VehicleumExchange.VEHICLE, RoutingKey.VEHICLE_STATE),
  DRIVER_DUTY("driver-duty", VehicleumExchange.DRIVER, RoutingKey.DRIVER_DUTY),
  ASSIGNATIONS("assignations", VehicleumExchange.DRIVER, RoutingKey.ASSIGNATION);

  private final String name;
  private final VehicleumExchange exchange;
  private final String deadLetterQueue;
  private final String pendingQueue;
  private final String deadLetterRoutingKey;
  private final String pendingRoutingKey;
  private final RoutingKey routingKey;
  private final Set<QueueFeature> features;

  VehicleumQueue(
      final String name,
      final VehicleumExchange exchange,
      final RoutingKey routingKey,
      final QueueFeature... features
  ) {
    this.name = name;
    this.exchange = exchange;
    this.routingKey = routingKey;
    this.deadLetterQueue = name + "-deadletter";
    this.deadLetterRoutingKey = routingKey.getKey() + ".deadletter";
    this.pendingQueue = name + "-pending";
    this.pendingRoutingKey = routingKey.getKey() + ".pending";
    this.features = stream(features).collect(toUnmodifiableSet());
  }

  public enum QueueFeature {
    SINGLE_ACTIVE_CONSUMER, PENDING;
  }

  public boolean supportsFeature(final QueueFeature feature) {
    return features.contains(feature);
  }
}
