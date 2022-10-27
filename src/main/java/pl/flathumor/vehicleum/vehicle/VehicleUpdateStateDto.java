package pl.flathumor.vehicleum.vehicle;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class VehicleUpdateStateDto {
  UUID id;

  @NotNull
  VehicleState state;
}
