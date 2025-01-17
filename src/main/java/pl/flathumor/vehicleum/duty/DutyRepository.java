package pl.flathumor.vehicleum.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DutyRepository extends JpaRepository<DutyEntity, UUID> {
}
