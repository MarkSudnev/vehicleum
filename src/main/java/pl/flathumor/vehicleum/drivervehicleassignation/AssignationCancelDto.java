package pl.flathumor.vehicleum.drivervehicleassignation;


import lombok.Builder;
import lombok.Value;
import pl.flathumor.vehicleum.shared.validation.NullOrNotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AssignationCancelDto {

  @NotNull
  UUID id;

  @NullOrNotBlank(message = "Note must not be blank")
  String note;
}
