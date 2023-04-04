package pl.flathumor.vehicleum.drivervehicleassignation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.flathumor.vehicleum.shared.SortMapping;
import pl.flathumor.vehicleum.shared.SortMappings;

import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver-vehicle-assignations")
public class DriverVehicleAssignationController {

  private final DriverVehicleAssignationService driverVehicleAssignationService;

  @GetMapping
  @SortMappings({
      @SortMapping(from = "driver", to = "driver.name"),
      @SortMapping(from = "vehicle", to = "vehicle.plate")
  })
  public AssignationGridDto getPaged(
      @RequestParam(required = false) final String search,
      @PageableDefault(sort = "id", direction = DESC) final Pageable pageable
  ) {
    return driverVehicleAssignationService.getPaged(search, pageable);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public UUID create(@Validated @RequestBody final AssignationCreateDto dto) {
    return driverVehicleAssignationService.create(dto);
  }

  @PatchMapping("/{id}")
  public void finish(
      @PathVariable final UUID id,
      @Validated @RequestBody final AssignationFinishDto dto
  ) {
    driverVehicleAssignationService.finish(id, dto);
  }

  @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
  public void cancel(
      @PathVariable final UUID id,
      @Validated @RequestBody final AssignationCancelDto dto
  ) {
    driverVehicleAssignationService.cancel(id, dto);
  }
}
