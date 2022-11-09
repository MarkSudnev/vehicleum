package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static pl.flathumor.vehicleum.rabbit.VehicleumQueue.VEHICLE_STATE;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleStateEventListener {

  private final VehicleService vehicleService;

  @RabbitListener(queues = "vehicle-state")
  public void handle(final VehicleStateEvent event) {
    log.debug("Message received: " + event.toString());
    vehicleService.updateVehicleState(event.getDocumentId(), event.getVehicleState());
  }
}
