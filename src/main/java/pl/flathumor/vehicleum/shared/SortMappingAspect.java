package pl.flathumor.vehicleum.shared;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Aspect
@Component
public class SortMappingAspect {

  @Around("within(pl.flathumor.vehicleum..*) " +
      "&& execution(* *(.., org.springframework.data.domain.Pageable, ..)) " +
      "&& @annotation(sortMappings)")
  @SneakyThrows
  public Object overrideSorting(final ProceedingJoinPoint joinPoint, final SortMappings sortMappings) {
    final var methodArguments = joinPoint.getArgs();
    final var existingPageable = ofNullable(methodArguments)
        .stream()
        .flatMap(Arrays::stream)
        .filter(Pageable.class::isInstance)
        .map(Pageable.class::cast)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No pageable found"));
    final var updatedPageable = updatePageable(existingPageable, sortMappings);
    final var updatedArguments = ofNullable(methodArguments)
        .stream()
        .flatMap(Arrays::stream)
        .map(arg -> existingPageable.equals(arg) ? updatedPageable : arg)
        .toArray();
    return joinPoint.proceed(updatedArguments);
  }

  private Pageable updatePageable(final Pageable pageable, final SortMappings mappings) {
    final var updatedSort = pageable.getSort().stream()
        .map(sort -> updateOrder(sort, mappings))
        .reduce(Sort.by(emptyList()), Sort::and);
    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), updatedSort);
  }

  private Sort updateOrder(final Sort.Order existingOrder, final SortMappings mappings) {
    return Stream.of(mappings.value())
        .filter(mapping -> mapping.from().equals(existingOrder.getProperty()))
        .map(mapping -> Sort.by(existingOrder.getDirection(), mapping.to()))
        .findFirst()
        .orElseGet(() -> Sort.by(existingOrder));
  }

}
