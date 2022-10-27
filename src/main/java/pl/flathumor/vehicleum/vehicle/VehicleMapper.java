package pl.flathumor.vehicleum.vehicle;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VehicleMapper {

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "brand", source = "brand")
  @Mapping(target = "model", source = "model")
  @Mapping(target = "year", source = "year")
  @Mapping(target = "plate", source = "plate")
  @Mapping(target = "vehicleState", source = "vehicleState")
  VehicleDetailDto entityToDetailDto(final VehicleEntity entity);
}
