package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.domain.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Optional<Area> findByCodeIgnoreCase(String code);

}