package az.restopia.integration.repository;

import az.restopia.integration.domain.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {

    Optional<Integration> findByCode(String code);
}
