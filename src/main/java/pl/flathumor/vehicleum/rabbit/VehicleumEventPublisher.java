package pl.flathumor.vehicleum.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleumEventPublisher implements EventPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publish(final VehicleumEvent event) {
    final var exchange = event.getQueue().getExchange();
    final var routingKey = event.getQueue().getRoutingKey();
    rabbitTemplate.convertAndSend(exchange.getName(), routingKey.getKey(), event);
  }
}
