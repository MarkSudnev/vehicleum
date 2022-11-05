package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleReparationEventHandler {

  private final VehicleService vehicleService;
}
