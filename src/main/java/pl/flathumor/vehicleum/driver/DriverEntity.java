package pl.flathumor.vehicleum.driver;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.shared.VehicleumEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "driver")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DriverEntity extends VehicleumEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "birth_date", nullable = false, columnDefinition = "date")
  private LocalDate birthDate;

  @Column(name = "phone", nullable = false, unique = true)
  private String phone;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "licence", nullable = false, unique = true)
  private String licence;
}
