package pl.flathumor.vehicleum.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationService;
import pl.flathumor.vehicleum.shared.NoResourceFoundException;

import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;
import static pl.flathumor.vehicleum.driver.DriverSpecifications.emailLike;
import static pl.flathumor.vehicleum.driver.DriverSpecifications.nameLike;
import static pl.flathumor.vehicleum.driver.DriverSpecifications.phoneLike;
import static pl.flathumor.vehicleum.shared.VehicleumEntityStatus.ACTIVE;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.activeEntityIdIs;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.statusIs;

@Service
@RequiredArgsConstructor
public class DriverService {

  private final DriverRepository driverRepository;
  private final DriverMapper driverMapper;
  private final DriverVehicleAssignationService driverVehicleAssignationService;

  @Transactional(readOnly = true)
  public DriverGridDto getPaged(final String search, final Pageable pageable) {
    final var specification = where(nameLike(search)
            .or(phoneLike(search))
            .or(emailLike(search))
        .and(statusIs(ACTIVE)));

    final var driversPage = driverRepository.findAll(specification, pageable)
        .map(driverMapper::entityToLineDto);

    return DriverGridDto.builder()
        .items(driversPage.getContent())
        .total(driversPage.getTotalElements())
        .build();
  }

  @Transactional(readOnly = true)
  public DriverDetailDto getById(final UUID id) {
    final var assignation =
        driverVehicleAssignationService.getCurrentDriverAssignation(id);

    return driverRepository.findOne(where(activeEntityIdIs(id)))
        .map(driver -> driverMapper.entityToDetailDto(driver, assignation.orElse(null)))
        .orElseThrow(() -> new NoResourceFoundException(DriverEntity.class.getSimpleName(), id));
  }
}
