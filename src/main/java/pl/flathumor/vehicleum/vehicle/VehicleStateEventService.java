package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.flathumor.vehicleum.rabbit.VehicleumEventPublisher;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class VehicleStateEventService {

  private final VehicleumEventPublisher eventPublisher;

  public void publishEvent(final UUID id, @Valid final VehicleUpdateStateDto dto) {
    final var event = VehicleStateEvent.builder()
        .id(UUID.randomUUID())
//        .timestamp(LocalDateTime.now())
        .documentId(id)
        .vehicleState(dto.getState())
        .build();

    eventPublisher.publish(event);
  }
}
