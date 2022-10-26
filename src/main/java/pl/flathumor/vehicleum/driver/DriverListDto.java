package pl.flathumor.vehicleum.driver;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DriverListDto {
  UUID id;
  String name;
  String phone;
  String email;
}
