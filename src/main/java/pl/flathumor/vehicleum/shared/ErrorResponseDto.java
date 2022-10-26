package pl.flathumor.vehicleum.shared;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class ErrorResponseDto {

  String category;
  Collection<ErrorDto> errors;
}
