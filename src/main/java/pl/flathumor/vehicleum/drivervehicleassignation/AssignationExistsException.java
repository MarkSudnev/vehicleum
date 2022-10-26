package pl.flathumor.vehicleum.drivervehicleassignation;

import org.springframework.web.bind.annotation.ResponseStatus;
import pl.flathumor.vehicleum.shared.VehicleumException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class AssignationExistsException extends VehicleumException {

  public AssignationExistsException(final String message) {
    super(message);
  }
}
