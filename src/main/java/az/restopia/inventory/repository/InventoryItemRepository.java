package az.restopia.inventory.repository;

import az.restopia.inventory.domain.entity.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Page<InventoryItem> findByTenantCodeAndInventoryId(String tenantCode, Long inventoryId, Pageable pageable);

    Optional<InventoryItem> findByIdAndTenantCodeAndInventoryId(Long id, String tenantCode, Long inventoryId);

    boolean existsByTenantCodeAndSku(String tenantCode, String sku);

    Optional<InventoryItem> findByIdAndTenantCode(Long id, String tenantCode);

    @Query("""
        SELECT i FROM InventoryItem i
        WHERE i.tenantCode = :tenantCode
        AND i.inventory.id = :inventoryId
        AND (:query IS NULL OR
             LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%')) OR
             LOWER(i.description) LIKE LOWER(CONCAT('%', :query, '%')) OR
             LOWER(i.sku) LIKE LOWER(CONCAT('%', :query, '%')))
        AND (:sku IS NULL OR LOWER(i.sku) LIKE LOWER(CONCAT('%', :sku, '%')))
        AND (:barcode IS NULL OR i.barcode = :barcode)
        AND (:perishable IS NULL OR i.perishable = :perishable)
        AND (:requiresRefrigeration IS NULL OR i.requiresRefrigeration = :requiresRefrigeration)
        """)
    Page<InventoryItem> searchItems(
            @Param("tenantCode") String tenantCode,
            @Param("inventoryId") Long inventoryId,
            @Param("query") String query,
            @Param("sku") String sku,
            @Param("barcode") String barcode,
            @Param("perishable") Boolean perishable,
            @Param("requiresRefrigeration") Boolean requiresRefrigeration,
            Pageable pageable);
}