package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("rabbit")
public class RabbitProperties {

  Queue queue;
  Message message;

  @Getter
  @Setter
  public static class Queue {
    Long maxLength;
    String overflow;
    String lazyMode;
  }

  @Getter
  @Setter
  public static class Message {
    Long ttl;
  }
}
