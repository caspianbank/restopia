package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    Optional<RestaurantTable> findByCodeIgnoreCase(String code);
}
