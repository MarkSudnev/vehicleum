package pl.flathumor.vehicleum.vehicle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.rabbit.VehicleumEvent;
import pl.flathumor.vehicleum.rabbit.VehicleumQueue;

import static pl.flathumor.vehicleum.rabbit.VehicleumQueue.VEHICLE_STATE;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VehicleStateEvent extends VehicleumEvent {

  VehicleState vehicleState;

  @Override
  public VehicleumQueue getQueue() {
    return VEHICLE_STATE;
  }
}
