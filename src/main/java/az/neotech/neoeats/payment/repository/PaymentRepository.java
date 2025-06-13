package az.neotech.neoeats.payment.repository;

import az.neotech.neoeats.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentId(String paymentId);

    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByReferenceNumber(String referenceNumber);

    Optional<Payment> findByOrderId(Long orderId);

    List<Payment> findByTenantCodeAndCustomerEmail(String tenantCode, String customerEmail);

    List<Payment> findByTenantCodeOrderByCreatedAtDesc(String tenantCode);

    @Query("SELECT p FROM Payment p WHERE p.tenantCode = :tenantCode AND p.status = :status")
    List<Payment> findByTenantCodeAndStatus(@Param("tenantCode") String tenantCode,
                                            @Param("status") az.neotech.neoeats.payment.domain.enums.PaymentStatus status);
}

