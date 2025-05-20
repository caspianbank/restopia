package az.neotech.neoeats.repository;

import az.neotech.neoeats.domain.entity.TenantBusinessStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantBusinessStoreRepository extends JpaRepository<TenantBusinessStore, Long> {
    List<TenantBusinessStore> findAllByBusinessId(Long businessId);
}
