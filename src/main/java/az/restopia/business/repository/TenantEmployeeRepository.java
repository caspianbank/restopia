package az.restopia.business.repository;

import az.restopia.business.domain.entity.TenantEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantEmployeeRepository extends JpaRepository<TenantEmployee, Long> {

}
