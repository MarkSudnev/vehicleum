package pl.flathumor.vehicleum.driver;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pl.flathumor.vehicleum.AbstractIntegrationTest;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DriverIntegrationTest extends AbstractIntegrationTest {

  @Test
  @SneakyThrows
  void shouldReturnDrivers() {
    final var response = mockMvc
        .perform(get("/drivers").contentType(APPLICATION_JSON))
        .andExpect(status().is(OK.value()));
    verifyResponseBody(response, "driver/json/get-drivers-paged-response.json");
  }
}
