package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.driver.DriverEntity;
import pl.flathumor.vehicleum.shared.VehicleumEntity;
import pl.flathumor.vehicleum.vehicle.VehicleEntity;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Table(name = "driver_vehicle_assignation")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DriverVehicleAssignationEntity extends VehicleumEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "driver_id")
  private DriverEntity driver;

  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_id")
  private VehicleEntity vehicle;

  @Column(name = "start_date", nullable = false, updatable = false)
  private LocalDateTime startDate;

  @Column(name = "finish_date")
  private LocalDateTime finishDate;

  @Column(name = "note")
  private String note;
}
