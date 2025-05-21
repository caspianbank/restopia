package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantEmployeeRepository extends JpaRepository<TenantEmployee, Long> {

}
