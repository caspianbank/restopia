package az.restopia.inventory.repository;

import az.restopia.inventory.domain.entity.InventoryWastage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryWastageRepository extends JpaRepository<InventoryWastage, Long> {

    Page<InventoryWastage> findByTenantCode(String tenantCode, Pageable pageable);

    Optional<InventoryWastage> findByIdAndTenantCode(Long id, String tenantCode);

    long countByTenantCode(String tenantCode);

    @Query("""
        SELECT w FROM InventoryWastage w 
        WHERE w.tenantCode = :tenantCode
        AND (:reference IS NULL OR LOWER(w.wastageReference) LIKE LOWER(CONCAT('%', :reference, '%')))
        AND (:reason IS NULL OR LOWER(w.reason) LIKE LOWER(CONCAT('%', :reason, '%')))
        AND (:status IS NULL OR w.status = :status)
        AND (:location IS NULL OR LOWER(w.location) LIKE LOWER(CONCAT('%', :location, '%')))
        AND (:inventoryItemId IS NULL OR w.inventoryItem.id = :inventoryItemId)
        """)
    Page<InventoryWastage> searchWastages(
            @Param("tenantCode") String tenantCode,
            @Param("reference") String reference,
            @Param("reason") String reason,
            @Param("status") String status,
            @Param("location") String location,
            @Param("inventoryItemId") Long inventoryItemId,
            Pageable pageable);
}