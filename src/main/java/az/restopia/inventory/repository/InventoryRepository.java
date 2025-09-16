package az.restopia.inventory.repository;

import az.restopia.inventory.domain.entity.Inventory;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByBusinessIdAndDeleteStatus(Long businessId, DeleteStatus deleteStatus);

    List<Inventory> findByBusinessIdAndBusinessStoreIdAndDeleteStatus(
            Long businessId, Long businessStoreId, DeleteStatus deleteStatus);

    Optional<Inventory> findByIdAndDeleteStatus(Long id, DeleteStatus deleteStatus);

    @Query("SELECT i FROM Inventory i WHERE i.business.id = :businessId AND i.businessStore IS NULL AND i.main = true AND i.deleteStatus = :deleteStatus")
    Optional<Inventory> findMainBusinessInventory(@Param("businessId") Long businessId, @Param("deleteStatus") DeleteStatus deleteStatus);

    @Query("SELECT i FROM Inventory i WHERE i.businessStore.id = :storeId AND i.main = true AND i.deleteStatus = :deleteStatus")
    Optional<Inventory> findMainStoreInventory(@Param("storeId") Long storeId, @Param("deleteStatus") DeleteStatus deleteStatus);
}