package pl.flathumor.vehicleum.driver;

import org.springframework.stereotype.Repository;
import pl.flathumor.vehicleum.shared.VehicleumRepository;

import java.util.UUID;

@Repository
public interface DriverRepository extends VehicleumRepository<DriverEntity, UUID> {
}
