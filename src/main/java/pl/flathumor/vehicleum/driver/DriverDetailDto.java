package pl.flathumor.vehicleum.driver;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class DriverDetailDto {
  UUID id;
  String name;
  LocalDate birthDate;
  String phone;
  String email;
  String licence;
  AssignationDto assignation;

  @Value
  @Builder
  public static class AssignationDto {
    UUID id;
    String plate;
    LocalDateTime startDate;
    LocalDateTime finishDate;
  }
}
