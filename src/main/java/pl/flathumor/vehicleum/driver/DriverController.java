package pl.flathumor.vehicleum.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.flathumor.vehicleum.shared.SortMapping;
import pl.flathumor.vehicleum.shared.SortMappings;

import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static pl.flathumor.vehicleum.shared.VehicleumEntity_.ID;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

  private final DriverService driverService;

  @GetMapping
  @SortMappings({
      @SortMapping(from = "name", to = "name"),
      @SortMapping(from = "phone", to = "phone"),
      @SortMapping(from = "email", to = "email")
  })
  public DriverGridDto getPaged(
      @RequestParam(required = false) final String search,
      @PageableDefault(sort = ID, direction = DESC) final Pageable pageable
  ) {
    return driverService.getPaged(search, pageable);
  }

  @GetMapping("/{id}")
  public DriverDetailDto getById(@PathVariable final UUID id) {
    return driverService.getById(id);
  }
}
