package pl.flathumor.vehicleum.drivervehicleassignation;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.flathumor.vehicleum.driver.DriverEntity;
import pl.flathumor.vehicleum.shared.VehicleumEntityStatus;
import pl.flathumor.vehicleum.vehicle.VehicleEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(imports = {LocalDateTime.class, UUID.class, VehicleumEntityStatus.class})
public interface DriverVehicleAssignationMapper {

  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "status", expression = "java(VehicleumEntityStatus.ACTIVE)")
  @Mapping(target = "createDate", expression = "java(LocalDateTime.now())")
  @Mapping(target = "modifyDate", expression = "java(LocalDateTime.now())")
  @Mapping(target = "driver.id", source = "dto.driverId")
  @Mapping(target = "vehicle.id", source = "dto.vehicleId")
  @Mapping(target = "startDate", source = "startDate")
  @Mapping(target = "finishDate", source = "finishDate")
  DriverVehicleAssignationEntity createDtoToEntity(final AssignationCreateDto dto);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "driver", source = "entity.driver")
  @Mapping(target = "vehicle", source = "entity.vehicle")
  @Mapping(target = "startDate", source = "startDate")
  @Mapping(target = "finishDate", source = "finishDate")
  @Mapping(target = "note", source = "note")
  AssignationDetailDto entityToDetailDto(final DriverVehicleAssignationEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "driver", source = "entity.driver")
  @Mapping(target = "vehicle", source = "entity.vehicle")
  @Mapping(target = "startDate", source = "startDate")
  @Mapping(target = "finishDate", source = "finishDate")
  @Mapping(target = "note", source = "note")
  AssignationListDto entityToListDto(final DriverVehicleAssignationEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "plate", source = "plate")
  AssignationDetailDto.VehicleDto toVehicleDetailDto(final VehicleEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  AssignationDetailDto.DriverDto toDriverDetailDto(final DriverEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "plate", source = "plate")
  AssignationListDto.VehicleDto toVehicleListDto(final VehicleEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  AssignationListDto.DriverDto toDriverListDto(final DriverEntity entity);
}
