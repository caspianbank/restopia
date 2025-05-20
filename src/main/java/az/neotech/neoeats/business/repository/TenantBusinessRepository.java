package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantBusinessRepository extends JpaRepository<TenantBusiness, Long> {
}
