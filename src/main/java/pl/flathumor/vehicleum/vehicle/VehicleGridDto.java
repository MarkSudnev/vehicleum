package pl.flathumor.vehicleum.vehicle;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class VehicleGridDto {

  Collection<VehicleDto> items;
  Long total;
}
