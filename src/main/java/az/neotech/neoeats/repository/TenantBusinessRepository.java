package az.neotech.neoeats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.neotech.neoeats.domain.entity.TenantBusiness;

@Repository
public interface TenantBusinessRepository extends JpaRepository<TenantBusiness, Long> {
}
