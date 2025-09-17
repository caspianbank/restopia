package az.restopia.inventory.repository;

import az.restopia.inventory.domain.entity.InventoryItemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemCategoryRepository extends JpaRepository<InventoryItemCategory, Long> {

    Optional<InventoryItemCategory> findByIdAndTenantCode(Long id, String tenantCode);

    Page<InventoryItemCategory> findByTenantCodeOrderBySortOrderAscNameAsc(String tenantCode, Pageable pageable);

    List<InventoryItemCategory> findByTenantCodeAndParentIsNullOrderBySortOrderAscNameAsc(String tenantCode);

    List<InventoryItemCategory> findByTenantCodeAndParentIdOrderBySortOrderAscNameAsc(String tenantCode, Long parentId);

    @Query("SELECT c FROM InventoryItemCategory c WHERE c.tenantCode = :tenantCode AND " +
            "(:parentId IS NULL AND c.parent IS NULL OR c.parent.id = :parentId) " +
            "ORDER BY c.sortOrder ASC, c.name ASC")
    List<InventoryItemCategory> findByTenantCodeAndOptionalParentId(@Param("tenantCode") String tenantCode,
                                                                    @Param("parentId") Long parentId);

    boolean existsByTenantCodeAndNameAndParentId(String tenantCode, String name, Long parentId);

    boolean existsByTenantCodeAndNameAndParentIdAndIdNot(String tenantCode, String name, Long parentId, Long id);
}