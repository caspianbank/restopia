package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.domain.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    // todo: no code in area entity
    Optional<Area> findByCodeIgnoreCase(String code);

}