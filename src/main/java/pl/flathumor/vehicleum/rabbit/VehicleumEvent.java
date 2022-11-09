package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class VehicleumEvent {

  protected UUID id;
  protected UUID documentId;
  protected LocalDateTime timestamp;

  public abstract VehicleumQueue getQueue();

}
