package pl.flathumor.vehicleum.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static java.util.Arrays.stream;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.DEAD_LETTER_EXCHANGE;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.DEAD_LETTER_ROUTING_KEY;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.LAZY_MODE;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.MAX_LENGTH;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.MESSAGE_TTL;
import static pl.flathumor.vehicleum.rabbit.QueueArgument.OVERFLOW;
import static pl.flathumor.vehicleum.rabbit.VehicleumQueue.QueueFeature.SINGLE_ACTIVE_CONSUMER;

@Component
@RequiredArgsConstructor
public class RabbitQueueService {

  private final AmqpAdmin amqpAdmin;
  private final RabbitProperties rabbitProperties;

  @PostConstruct
  public void postConstruct() {
    declareExchanges();
    declareQueues();
  }

  private void declareExchanges() {
    stream(VehicleumExchange.values())
        .map(VehicleumExchange::getName)
        .map(DirectExchange::new)
        .forEach(amqpAdmin::declareExchange);
  }

  private void declareQueues() {
    stream(VehicleumQueue.values()).forEach(this::initializeQueue);
  }

  private void initializeQueue(final VehicleumQueue vehicleumQueue) {
    final var deadLetterQueue = new Queue(vehicleumQueue.getDeadLetterQueue());
    amqpAdmin.declareQueue(deadLetterQueue);

    final var queue = createQueue(vehicleumQueue, deadLetterQueue);
    amqpAdmin.declareQueue(queue);

    final var queueBinding = BindingBuilder
        .bind(queue)
        .to(new DirectExchange(vehicleumQueue.getExchange().getName()))
        .with(vehicleumQueue.getRoutingKey().getKey());
    amqpAdmin.declareBinding(queueBinding);

    final var pendingQueue = createPendingQueue(vehicleumQueue, deadLetterQueue);
    amqpAdmin.declareQueue(pendingQueue);

    final var pendingQueueBinding = BindingBuilder
        .bind(pendingQueue)
        .to(new DirectExchange(vehicleumQueue.getExchange().getName()))
        .with(vehicleumQueue.getPendingRoutingKey());
    amqpAdmin.declareBinding(pendingQueueBinding);
  }

  private Queue createQueue(final VehicleumQueue vehicleumQueue, final Queue deadLetterQueue) {
    final var builder = queueBuilder(vehicleumQueue.getName(), deadLetterQueue);
    if (vehicleumQueue.supportsFeature(SINGLE_ACTIVE_CONSUMER)) {
      builder.singleActiveConsumer();
    }
    return builder.build();
  }

  private Queue createPendingQueue(final VehicleumQueue vehicleumQueue, final Queue deadLetterQueue) {
    return queueBuilder(vehicleumQueue.getPendingQueue(), deadLetterQueue).build();
  }

  private QueueBuilder queueBuilder(final String queueName, final Queue deadLetterQueue) {
    return QueueBuilder.durable(queueName)
        .withArgument(DEAD_LETTER_ROUTING_KEY.getKey(), deadLetterQueue.getActualName())
        .withArgument(DEAD_LETTER_EXCHANGE.getKey(), "")
        .withArgument(MESSAGE_TTL.getKey(), rabbitProperties.getMessage().getTtl())
        .withArgument(MAX_LENGTH.getKey(), rabbitProperties.getQueue().getMaxLength())
        .withArgument(LAZY_MODE.getKey(), rabbitProperties.getQueue().getLazyMode())
        .withArgument(OVERFLOW.getKey(), rabbitProperties.getQueue().getOverflow());
  }

}
