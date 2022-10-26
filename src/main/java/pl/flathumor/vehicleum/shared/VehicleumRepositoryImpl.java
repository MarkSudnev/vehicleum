package pl.flathumor.vehicleum.shared;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphSimpleJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class VehicleumRepositoryImpl<T, ID extends Serializable> extends EntityGraphSimpleJpaRepository<T, ID> {

  public VehicleumRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
  }
}
