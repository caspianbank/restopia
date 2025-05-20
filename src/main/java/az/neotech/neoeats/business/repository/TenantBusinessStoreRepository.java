package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantBusinessStoreRepository extends JpaRepository<TenantBusinessStore, Long> {
    List<TenantBusinessStore> findAllByBusinessId(Long businessId);
}
