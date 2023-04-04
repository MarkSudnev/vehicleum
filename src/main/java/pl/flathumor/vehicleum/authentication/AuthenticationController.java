package pl.flathumor.vehicleum.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  @GetMapping("/implicit/callback")
  @ResponseStatus(OK)
  public void implicit(@RequestParam("access_token") final String token) {
    log.info("Got token: " + token);
  }

  @GetMapping("/code")
  public void authorizationCode(@RequestParam("code") final String code) {
    log.info("Got code: " + code);
  }
}
