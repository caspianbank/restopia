package az.restopia.payment.repository;

import az.restopia.payment.domain.entity.Payment;
import az.restopia.payment.domain.enums.PaymentStatus;
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

    Optional<Payment> findByOrderId(String orderId);

    List<Payment> findByTenantCodeAndCustomerEmail(String tenantCode, String customerEmail);

    List<Payment> findByTenantCodeOrderByCreatedDateTimeDesc(String tenantCode);

    @Query("SELECT p FROM Payment p WHERE p.tenantCode = :tenantCode AND p.status = :status")
    List<Payment> findByTenantCodeAndStatus(@Param("tenantCode") String tenantCode,
                                            @Param("status") PaymentStatus status);
}

