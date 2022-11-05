package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleReparationListener {

  private final VehicleService vehicleService;

  @RabbitListener(queues = {"vehicle-reparation"})
  public void handle(final VehicleReparationEvent event) {
    final var dto = VehicleUpdateStateDto.builder()
        .id(event.getDocumentId())
        .state(event.getVehicleState())
        .build();
    vehicleService.updateVehicleState(event.getDocumentId(), dto);
  }
}
