package pl.flathumor.vehicleum.driver;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.flathumor.vehicleum.drivervehicleassignation.DriverVehicleAssignationEntity;

@Mapper
public interface DriverMapper {

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "phone", source = "phone")
  @Mapping(target = "email", source = "email")
  DriverListDto entityToLineDto(final DriverEntity entity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "entity.id")
  @Mapping(target = "name", source = "entity.name")
  @Mapping(target = "birthDate", source = "entity.birthDate")
  @Mapping(target = "phone", source = "entity.phone")
  @Mapping(target = "email", source = "entity.email")
  @Mapping(target = "licence", source = "entity.licence")
  @Mapping(target = "assignation", source = "assignation")
  DriverDetailDto entityToDetailDto(final DriverEntity entity, final DriverVehicleAssignationEntity assignation);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "plate", source = "assignation.vehicle.plate")
  @Mapping(target = "startDate", source = "startDate")
  @Mapping(target = "finishDate", source = "finishDate")
  DriverDetailDto.AssignationDto toAssignationDto(final DriverVehicleAssignationEntity assignation);
}
