package pl.flathumor.vehicleum.shared;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Data
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class VehicleumEntity extends VehicleumAuditableEntity {

  @Id
  @GeneratedValue
  private UUID id;
}
