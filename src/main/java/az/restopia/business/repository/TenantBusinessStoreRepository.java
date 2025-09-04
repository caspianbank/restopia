package az.restopia.business.repository;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantBusinessStoreRepository extends JpaRepository<TenantBusinessStore, Long> {
    List<TenantBusinessStore> findAllByBusinessId(Long businessId);

    List<TenantBusinessStore> findAllByBusiness(TenantBusiness business);

    @Query("SELECT s FROM TenantBusinessStore s WHERE s.id = :id " +
            "AND s.business.tenantCode = :tenantCode AND s.deleteStatus = :deleteStatus")
    Optional<TenantBusinessStore> findByIdAndBusinessTenantCodeAndDeleteStatus(
            @Param("id") Long id,
            @Param("tenantCode") String tenantCode,
            @Param("deleteStatus") DeleteStatus deleteStatus
    );
}
