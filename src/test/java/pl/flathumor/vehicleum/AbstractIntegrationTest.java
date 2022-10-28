package pl.flathumor.vehicleum;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;
import net.javacrumbs.jsonunit.core.internal.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.ResourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "60000")
@ActiveProfiles({"test"})
public abstract class AbstractIntegrationTest {

  protected static final PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:10");

  static {
    log.info("starting Postgres Container");
//    postgresContainer.withInitScript("db/init.sql");
    postgresContainer.withDatabaseName("vehicleum");
    postgresContainer.withUsername("root");
    postgresContainer.withPassword("root");
    postgresContainer.start();
  }

  @Value("${mock.server.port:8999}")
  protected int mockServerPort;

  @Autowired
  protected MockMvc mockMvc;

  @DynamicPropertySource
  static void commonProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }

  @SneakyThrows
  protected static String loadResourceAsString(final String filePath) {
    return Files.readString(
        ResourceUtils.getFile(CLASSPATH_URL_PREFIX + filePath).toPath(),
        UTF_8);
  }

  @SneakyThrows
  protected void verifyResponseBody(final ResultActions result, final String responseJsonPath) {
    final var expectedResponse = responseJsonPath.isBlank()
        ? EMPTY
        : loadResourceAsString(responseJsonPath);
    final var actualResponse = result.andReturn().getResponse().getContentAsString();
    assertThatJson(actualResponse)
        .withConfiguration(this::setupConfiguration)
        .isEqualTo(expectedResponse);
  }

  private Configuration setupConfiguration(final Configuration configuration) {
    var config = Configuration.empty().withOptions(new Options(Option.IGNORING_ARRAY_ORDER));
    // TODO: matchers
    return config;
  }
}
