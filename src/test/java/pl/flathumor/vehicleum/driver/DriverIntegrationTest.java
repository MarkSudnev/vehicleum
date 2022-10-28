package pl.flathumor.vehicleum.driver;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.flathumor.vehicleum.AbstractIntegrationTest;

public class DriverIntegrationTest extends AbstractIntegrationTest {

  @Test
  @SneakyThrows
  void shouldReturnDrivers() {
    final var response = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/drivers")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    verifyResponseBody(response, "driver/json/get-drivers-paged-response.json");
  }
}
