package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class AssignationFinishDto {

  @NotNull
  UUID id;

  @NotNull
  LocalDateTime finishDate;
}
