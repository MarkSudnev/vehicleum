package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class AssignationGridDto {
  Collection<AssignationListDto> items;
  Long total;
}
