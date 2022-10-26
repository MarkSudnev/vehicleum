package pl.flathumor.vehicleum.shared;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
  String code;
  String message;
}
