package az.neotech.neoeats.order.repository;

import az.neotech.neoeats.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByTenantCode(String tenantCode);
}
