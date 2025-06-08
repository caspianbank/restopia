package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.domain.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    Optional<RestaurantTable> findByCodeIgnoreCase(String code);

    Optional<RestaurantTable> findByQrCode(String qrCode);
}
