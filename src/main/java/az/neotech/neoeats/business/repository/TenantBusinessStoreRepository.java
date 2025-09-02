package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantBusinessStoreRepository extends JpaRepository<TenantBusinessStore, Long> {
    List<TenantBusinessStore> findAllByBusinessId(Long businessId);

    List<TenantBusinessStore> findAllByBusiness(TenantBusiness business);
}
