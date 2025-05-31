package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.entity.Area;
import az.neotech.neoeats.layout.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Optional<Area> findByCodeIgnoreCase(String code);

}