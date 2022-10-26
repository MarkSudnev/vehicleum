package pl.flathumor.vehicleum.driver;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class DriverGridDto {

  Collection<DriverListDto> items;
  Long total;
}
