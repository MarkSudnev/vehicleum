package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.flathumor.vehicleum.shared.NoResourceFoundException;
import pl.flathumor.vehicleum.shared.VehicleumSpecifications;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphType.FETCH;
import static com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils.fromAttributePaths;
import static org.springframework.data.jpa.domain.Specification.where;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationSpecifications.*;
import static pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationSpecifications.assignationPeriodIs;
import static pl.flathumor.vehicleum.shared.VehicleumEntityStatus.ACTIVE;
import static pl.flathumor.vehicleum.shared.VehicleumEntityStatus.INACTIVE;
import static pl.flathumor.vehicleum.shared.VehicleumSpecifications.statusIs;

@Service
@RequiredArgsConstructor
public class DriverVehicleAssignationService {

  private final DriverVehicleAssignationRepository driverVehicleAssignationRepository;
  private final DriverVehicleAssignationMapper driverVehicleAssignationMapper;

  @Transactional(readOnly = true)
  public AssignationGridDto getPaged(final String search, final Pageable pageable) {
    final var assignationsPage = driverVehicleAssignationRepository
        .findAll(
            where(driverNameLike(search).or(vehiclePlateLike(search))),
            pageable,
            fromAttributePaths(FETCH, "driver", "vehicle"))
        .map(driverVehicleAssignationMapper::entityToListDto);

    return AssignationGridDto.builder()
        .items(assignationsPage.getContent())
        .total(assignationsPage.getTotalElements())
        .build();
  }

  @Transactional(propagation = MANDATORY)
  public Optional<DriverVehicleAssignationEntity> getCurrentDriverAssignation(final UUID driverId) {
    return driverVehicleAssignationRepository.findOne(
        where(driverIs(driverId)
            .and(onDate(LocalDateTime.now()))
            .and(statusIs(ACTIVE))),
        fromAttributePaths(FETCH, "driver", "vehicle"));
  }

  @Transactional(propagation = MANDATORY)
  public Optional<DriverVehicleAssignationEntity> getCurrentVehicleAssignation(final UUID vehicleId) {
    return driverVehicleAssignationRepository.findOne(
        where(vehicleIs(vehicleId)
            .and(onDate(LocalDateTime.now()))
            .and(statusIs(ACTIVE))),
        fromAttributePaths(FETCH, "driver", "vehicle"));
  }

  @Transactional
  public UUID create(final AssignationCreateDto dto) {
    checkAvailability(dto);
    final var entity = driverVehicleAssignationMapper.createDtoToEntity(dto);
    driverVehicleAssignationRepository.save(entity);
    return entity.getId();
  }

  @Transactional
  public void cancel(final UUID assignationId, final AssignationCancelDto dto) {
    final var specification = where(
        VehicleumSpecifications.<DriverVehicleAssignationEntity>idIs(assignationId).and(statusIs(ACTIVE)));
    final var assignation = driverVehicleAssignationRepository
        .findOne(specification)
        .orElseThrow(() -> new NoResourceFoundException(DriverVehicleAssignationEntity.class, assignationId));
    assignation.setStatus(INACTIVE);
    assignation.setNote(dto.getNote());
  }

  private void checkAvailability(final AssignationCreateDto dto) {
    final var existingDriverAssignations = driverVehicleAssignationRepository.count(
        where(driverIs(dto.getDriverId())
            .and(statusIs(ACTIVE))
            .and(assignationPeriodIs(dto.getStartDate(), dto.getFinishDate()))));
    if (existingDriverAssignations > 0) {
      throw new AssignationExistsException(
          String.format("Assignation for driver [%s] already exists", dto.getDriverId()));
    }

    final var existingVehicleAssignations = driverVehicleAssignationRepository.count(
        where(vehicleIs(dto.getVehicleId())
            .and(statusIs(ACTIVE))
            .and(assignationPeriodIs(dto.getStartDate(), dto.getFinishDate()))));
    if (existingVehicleAssignations > 0) {
      throw new AssignationExistsException(
          String.format("Assignation for vehicle [%s] already exists", dto.getVehicleId()));
    }
  }

}
