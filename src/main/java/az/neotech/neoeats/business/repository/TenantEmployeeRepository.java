package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantEmployeeRepository extends JpaRepository<TenantEmployee, Long> {

}
