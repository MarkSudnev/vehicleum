package pl.flathumor.vehicleum.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;

  @GetMapping
  public VehicleGridDto getPaged(
      @RequestParam(required = false, defaultValue = "") final String search,
      @PageableDefault final Pageable pageable
  ) {
    return vehicleService.getPaged(search, pageable);
  }

  @GetMapping("/{id}")
  public VehicleDetailDto getById(@PathVariable final UUID id) {
    return vehicleService.getById(id);
  }

  @PatchMapping("/{id}")
  public void updateVehicleState(
      @PathVariable final UUID id,
      @Validated @RequestBody final VehicleUpdateStateDto dto
  ) {
    vehicleService.updateVehicleState(id, dto);
  }
}
