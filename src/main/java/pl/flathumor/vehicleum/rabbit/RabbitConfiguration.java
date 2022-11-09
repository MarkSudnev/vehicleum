package pl.flathumor.vehicleum.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

import static org.springframework.amqp.core.AcknowledgeMode.AUTO;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

  @Bean
  Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  RabbitTemplate rabbitTemplate(
      final CachingConnectionFactory cachingConnectionFactory,
      final Jackson2JsonMessageConverter jackson2JsonMessageConverter
  ) {
    final var rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  RetryOperationsInterceptor retryOperationsInterceptor() {
    return RetryInterceptorBuilder.stateless()
        .maxAttempts(3)
        .backOffOptions(2000, 2.0, 100000)
        .build();
  }

  @Bean
  SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
      final SimpleRabbitListenerContainerFactoryConfigurer configurer,
      final CachingConnectionFactory cachingConnectionFactory,
      final RetryOperationsInterceptor retryOperationsInterceptor
  ) {
    final var factory = new SimpleRabbitListenerContainerFactory();
    configurer.configure(factory, cachingConnectionFactory);
    factory.setAcknowledgeMode(AUTO);
    factory.setAdviceChain(retryOperationsInterceptor);
    return factory;
  }

  @Bean
  AmqpAdmin amqpAdmin(final CachingConnectionFactory cachingConnectionFactory) {
    return new RabbitAdmin(cachingConnectionFactory);
  }
}
