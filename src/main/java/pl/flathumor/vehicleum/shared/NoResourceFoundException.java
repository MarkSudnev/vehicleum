package pl.flathumor.vehicleum.shared;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NoResourceFoundException extends VehicleumException {

  public NoResourceFoundException(final String resourceName, final UUID id) {
    super(format("%s [%s] not found", resourceName, id));
  }

  public NoResourceFoundException(final Class<?> resourceType, final UUID id) {
    super(format("%s [%s] not found", resourceType.getSimpleName(), id));
  }
}
