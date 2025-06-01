package az.neotech.neoeats.inventory.repository;

import az.neotech.neoeats.inventory.domain.entity.InventoryDispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDispatchRepository extends JpaRepository<InventoryDispatch, Long> {
}