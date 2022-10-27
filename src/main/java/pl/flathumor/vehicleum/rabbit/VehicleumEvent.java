package pl.flathumor.vehicleum.rabbit;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class VehicleumEvent {

  private UUID id;
  private UUID documentId;
  private LocalDateTime timestamp;

  public abstract VehicleumQueue getQueue();

}
