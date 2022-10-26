package pl.flathumor.vehicleum.shared;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface VehicleumRepository<T, ID> extends
    Repository<T, ID>,
    EntityGraphJpaSpecificationExecutor<T> {

  T save(final T entity);
}
