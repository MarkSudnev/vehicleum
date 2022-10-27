package pl.flathumor.vehicleum.rabbit;

public interface EventPublisher {
  void publish(final VehicleumEvent event);
}
