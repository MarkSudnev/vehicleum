package pl.flathumor.vehicleum.duty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.flathumor.vehicleum.driver.DriverEntity;
import pl.flathumor.vehicleum.shared.VehicleumEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "duty")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DutyEntity extends VehicleumEntity {

  @ManyToOne(cascade = ALL, optional = false)
  @JoinColumn(name = "driver_id")
  private DriverEntity driver;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "finish_date")
  private LocalDateTime finishDate;

  @Enumerated(STRING)
  @Column(name = "duty_type", nullable = false)
  private DutyType dutyType;
}
