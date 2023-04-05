package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.flathumor.vehicleum.shared.NoResourceFoundException;
import pl.flathumor.vehicleum.shared.validation.Exists;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.activeEntityIdIs;
import static pl.flathumor.vehicleum.vehicle.VehicleSpecifications.plateLike;

@Service
@Validated
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;

  @Transactional(readOnly = true)
  public VehicleGridDto getPaged(final String search, final Pageable pageable) {
    final var vehiclesPage = vehicleRepository.findAll(where(plateLike(search)), pageable)
        .map(vehicleMapper::entityToDetailDto);

    return VehicleGridDto.builder()
        .items(vehiclesPage.getContent())
        .total(vehiclesPage.getTotalElements())
        .build();
  }

  @Transactional(readOnly = true)
  public VehicleDetailDto getById(final UUID id) {
    return vehicleRepository.findOne(where(activeEntityIdIs(id)))
        .map(vehicleMapper::entityToDetailDto)
        .orElseThrow(() -> new NoResourceFoundException(VehicleEntity.class.getSimpleName(), id));
  }

  @Transactional
  public void updateVehicleState(
      @Exists(entityClass = VehicleEntity.class) final UUID vehicleId,
      @Valid final VehicleUpdateStateDto dto
  ) {
    final var vehicle = vehicleRepository
        .findOne(where(activeEntityIdIs(vehicleId)))
        .orElseThrow(() -> new NoResourceFoundException(VehicleEntity.class.getSimpleName(), vehicleId));
    vehicle.setVehicleState(dto.getState());
  }

  @Transactional
  public void updateVehicleState(final UUID vehicleId, final VehicleState state) {
    final var vehicle = vehicleRepository
        .findOne(where(activeEntityIdIs(vehicleId)))
        .orElseThrow(() -> new NoResourceFoundException(VehicleEntity.class.getSimpleName(), vehicleId));
    vehicle.setVehicleState(state);
  }
}
