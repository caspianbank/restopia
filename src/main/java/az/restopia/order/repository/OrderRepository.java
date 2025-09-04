package az.restopia.order.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByTenantCode(String tenantCode);

    Page<Order> findByTenantCodeAndDeleteStatusAndStatus(
            String tenantCode,
            DeleteStatus deleteStatus,
            OrderStatus status,
            Pageable pageable
    );

    Page<Order> findByTenantCodeAndDeleteStatus(
            String tenantCode,
            DeleteStatus deleteStatus,
            Pageable pageable
    );

    Optional<Order> findByIdAndTenantCodeAndDeleteStatus(
            String id,
            String tenantCode,
            DeleteStatus deleteStatus
    );

    @Query("SELECT o FROM Order o WHERE o.tenantCode = :tenantCode " +
            "AND o.deleteStatus = :deleteStatus " +
            "AND (LOWER(o.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(o.customerName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR EXISTS (SELECT 1 FROM OrderItem oi JOIN oi.menuItem mi " +
            "WHERE oi.order = o AND LOWER(mi.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))))")
    Page<Order> searchOrders(
            @Param("tenantCode") String tenantCode,
            @Param("deleteStatus") DeleteStatus deleteStatus,
            @Param("searchTerm") String searchTerm,
            Pageable pageable
    );

    @Query("SELECT COUNT(o) FROM Order o WHERE o.tenantCode = :tenantCode " +
            "AND o.deleteStatus = :deleteStatus " +
            "AND o.createdDate BETWEEN :startDate AND :endDate")
    long countOrdersByDateRange(
            @Param("tenantCode") String tenantCode,
            @Param("deleteStatus") DeleteStatus deleteStatus,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
