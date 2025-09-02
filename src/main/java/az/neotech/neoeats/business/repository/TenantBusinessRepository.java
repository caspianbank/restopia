package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantBusinessRepository extends JpaRepository<TenantBusiness, Long> {
    Optional<TenantBusiness> findAllByTenantCode(String tenantCode);
}
