package pl.flathumor.vehicleum.shared.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

import static pl.flathumor.vehicleum.shared.VehicleumEntity_.ID;

@RequiredArgsConstructor
public class ExistsValidator implements ConstraintValidator<Exists, UUID> {

  @Autowired
  private EntityManager em;

  private Class<?> entityClass;

  @Override
  public void initialize(final Exists constraintAnnotation) {
    this.entityClass = constraintAnnotation.entityClass();
  }

  @Override
  public boolean isValid(final UUID id, final ConstraintValidatorContext context) {
    return isExists(id);
  }

  private boolean isExists(final UUID id) {
    final var cb = em.getCriteriaBuilder();
    final var query = cb.createQuery();
    final var root = query.from(entityClass);

    query
        .select(root.get(ID))
        .where(cb.equal(root.get(ID), id));

    return em
        .createQuery(query)
        .setMaxResults(1)
        .getSingleResult() != null;
  }
}
