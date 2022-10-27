package pl.flathumor.vehicleum.vehicle;

import org.springframework.stereotype.Repository;
import pl.flathumor.vehicleum.shared.VehicleumRepository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends VehicleumRepository<VehicleEntity, UUID> {
}
