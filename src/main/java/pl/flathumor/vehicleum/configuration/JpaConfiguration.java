package pl.flathumor.vehicleum.configuration;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.flathumor.vehicleum.shared.VehicleumRepositoryImpl;

@Configuration
@EnableJpaRepositories(
    value = "pl.flathumor.vehicleum",
    repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class,
    repositoryBaseClass = VehicleumRepositoryImpl.class)
public class JpaConfiguration {
}
