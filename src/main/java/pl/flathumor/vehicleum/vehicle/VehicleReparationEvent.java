package pl.flathumor.vehicleum.vehicle;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.rabbit.VehicleumEvent;
import pl.flathumor.vehicleum.rabbit.VehicleumQueue;

import static pl.flathumor.vehicleum.rabbit.VehicleumQueue.VEHICLE_REPARATION;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VehicleReparationEvent extends VehicleumEvent {

  VehicleState vehicleState;

  @Override
  public VehicleumQueue getQueue() {
    return VEHICLE_REPARATION;
  }
}
