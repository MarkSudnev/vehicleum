package pl.flathumor.vehicleum.shared;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class VehicleumAuditableEntity {

  @Enumerated(STRING)
  @Column(nullable = false)
  private VehicleumEntityStatus status;

  @Column(name = "create_date", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "modify_date",nullable = false)
  @LastModifiedDate
  private LocalDateTime modifyDate;
}
