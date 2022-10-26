package pl.flathumor.vehicleum.shared;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

import static pl.flathumor.vehicleum.shared.VehicleumEntityStatus.ACTIVE;

@UtilityClass
public class VehicleumSpecifications {

  public static <T, V> Specification<T> fieldIs(final String fieldName, final V value) {
    return (root, query, cb) -> value != null ? cb.equal(root.get(fieldName), value) : null;
  }

  public static <T> Specification<T> fieldLike(final String fieldName, final String value) {
    return (root, query, cb) -> value == null
        ? null
        : cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
  }

  public static <T, V> Specification<T> relatedFieldIdIs(final String fieldName, final V value) {
    return (root, query, cb) -> value != null ? cb.equal(root.get(fieldName).get("id"), value) : null;
  }

  public static <T> Specification<T> relatedFieldLike(
      final String relationName,
      final String fieldName,
      final String value
  ) {
    return (root, query, cb) -> value == null
        ? null
        : cb.like(cb.lower(root.get(relationName).get(fieldName)), "%" + value.toLowerCase() + "%");
  }

  public static <T extends VehicleumEntity> Specification<T> statusIs(final VehicleumEntityStatus status) {
    return (root, query, cb) -> status != null ? cb.equal(root.get("status"), status) : null;
  }

  public static <T extends VehicleumEntity> Specification<T> idIs(final UUID id) {
    return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : null;
  }

  public static <T extends VehicleumEntity> Specification<T> activeEntityIdIs(final UUID id) {
    return VehicleumSpecifications.<T>idIs(id).and(statusIs(ACTIVE));
  }
}
