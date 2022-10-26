package pl.flathumor.vehicleum.vehicle;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.shared.VehicleumEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "vehicle")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VehicleEntity extends VehicleumEntity {

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "production_year", nullable = false)
  private int year;

  @Column(name = "plate", nullable = false)
  private String plate;

  @Column(name = "vehicle_state", nullable = false)
  @Enumerated(STRING)
  private VehicleState vehicleState;
}
