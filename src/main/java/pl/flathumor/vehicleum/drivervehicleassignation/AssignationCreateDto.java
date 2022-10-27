package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import pl.flathumor.vehicleum.shared.validation.NullOrNotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class AssignationCreateDto {

  @NotNull
  UUID driverId;

  @NotNull
  UUID vehicleId;

  @NotNull
  LocalDateTime startDate;

  LocalDateTime finishDate;

  @NullOrNotBlank(message = "Note must not be blank")
  String note;
}
