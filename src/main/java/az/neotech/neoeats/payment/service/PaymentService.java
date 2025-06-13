package az.neotech.neoeats.payment.service;

import az.neotech.neoeats.payment.domain.request.PaymentRequest;
import az.neotech.neoeats.payment.domain.response.PaymentResponse;
import az.neotech.neoeats.payment.domain.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    PaymentResponse getPaymentByPaymentId(String paymentId);

    PaymentResponse getPaymentByTransactionId(String transactionId);

    PaymentResponse getPaymentByReferenceNumber(String referenceNumber);

    PaymentResponse getPaymentByOrderId(Long orderId);

    List<PaymentResponse> getPaymentsByTenantCode(String tenantCode);

    List<PaymentResponse> getPaymentsByTenantCodeAndStatus(String tenantCode, PaymentStatus status);

    List<PaymentResponse> getPaymentsByCustomerEmail(String tenantCode, String customerEmail);

    PaymentResponse updatePaymentStatus(Long id, PaymentStatus status, String failureReason);

    void deletePayment(Long id);

    boolean existsByPaymentId(String paymentId);

    boolean existsByTransactionId(String transactionId);

}
