package az.neotech.neoeats.inventory.repository;

import az.neotech.neoeats.inventory.domain.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findAll();

    Optional<InventoryItem> findById(Long id);
}
