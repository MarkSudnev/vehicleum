package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;

  @Transactional(readOnly = true)
  public VehicleGridDto getPaged(final Pageable pageable) {
    final var vehicles = vehicleRepository.findAll(pageable)
        .map(vehicleMapper::entityToDto);

    return VehicleGridDto.builder()
        .items(vehicles.getContent())
        .total(vehicles.getTotalElements())
        .build();
  }
}
