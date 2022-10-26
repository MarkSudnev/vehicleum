package pl.flathumor.vehicleum.drivervehicleassignation;

import org.springframework.stereotype.Repository;
import pl.flathumor.vehicleum.shared.VehicleumRepository;

import java.util.UUID;

@Repository
public interface DriverVehicleAssignationRepository
    extends VehicleumRepository<DriverVehicleAssignationEntity, UUID> {
}
