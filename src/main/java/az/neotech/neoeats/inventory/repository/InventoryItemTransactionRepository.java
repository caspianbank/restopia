package az.neotech.neoeats.inventory.repository;

import az.neotech.neoeats.inventory.domain.entity.InventoryItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemTransactionRepository extends JpaRepository<InventoryItemTransaction, Long> {
}
