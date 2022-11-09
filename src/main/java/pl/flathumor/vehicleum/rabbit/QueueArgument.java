package pl.flathumor.vehicleum.rabbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QueueArgument {
  DEAD_LETTER_EXCHANGE("x-dead-letter-exchange"),
  DEAD_LETTER_ROUTING_KEY("x-dead-letter-routing-key"),
  MESSAGE_TTL("x-message-ttl"),
  MAX_LENGTH("x-max-length"),
  LAZY_MODE("x-lazy-mode"),
  OVERFLOW("x-overflow");

  private final String key;
}

