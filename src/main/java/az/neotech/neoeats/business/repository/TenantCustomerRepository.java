package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantCustomerRepository extends JpaRepository<TenantCustomer, Long> {

    Optional<TenantCustomer> findByPhoneNumberAndTenantCode(String phone, String tenantCode);
}
