package az.neotech.neoeats.payment.controller;

import az.neotech.neoeats.payment.domain.enums.PaymentStatus;
import az.neotech.neoeats.payment.domain.request.PaymentRequest;
import az.neotech.neoeats.payment.domain.response.PaymentResponse;
import az.neotech.neoeats.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest request) {
        log.info("Creating payment for order ID: {}", request.getOrderId());
        return paymentService.createPayment(request);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        log.debug("Fetching payment by ID: {}", id);
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/payment-id/{paymentId}")
    public PaymentResponse getPaymentByPaymentId(@PathVariable String paymentId) {
        log.debug("Fetching payment by payment ID: {}", paymentId);
        return paymentService.getPaymentByPaymentId(paymentId);
    }

    @GetMapping("/transaction-id/{transactionId}")
    public PaymentResponse getPaymentByTransactionId(@PathVariable String transactionId) {
        log.debug("Fetching payment by transaction ID: {}", transactionId);
        return paymentService.getPaymentByTransactionId(transactionId);
    }

    @GetMapping("/reference-number/{referenceNumber}")
    public PaymentResponse getPaymentByReferenceNumber(@PathVariable String referenceNumber) {
        log.debug("Fetching payment by reference number: {}", referenceNumber);
        return paymentService.getPaymentByReferenceNumber(referenceNumber);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId) {
        log.debug("Fetching payment by order ID: {}", orderId);
        return paymentService.getPaymentByOrderId(orderId);
    }

    @GetMapping("/tenant/{tenantCode}")
    public List<PaymentResponse> getPaymentsByTenantCode(@PathVariable String tenantCode) {
        log.debug("Fetching payments for tenant: {}", tenantCode);
        return paymentService.getPaymentsByTenantCode(tenantCode);
    }

    @GetMapping("/tenant/{tenantCode}/status/{status}")
    public List<PaymentResponse> getPaymentsByTenantCodeAndStatus(
            @PathVariable String tenantCode,
            @PathVariable PaymentStatus status) {
        log.debug("Fetching payments for tenant: {} with status: {}", tenantCode, status);
        return paymentService.getPaymentsByTenantCodeAndStatus(tenantCode, status);
    }

    @GetMapping("/tenant/{tenantCode}/customer/{customerEmail}")
    public List<PaymentResponse> getPaymentsByCustomerEmail(
            @PathVariable String tenantCode,
            @PathVariable String customerEmail) {
        log.debug("Fetching payments for tenant: {} and customer: {}", tenantCode, customerEmail);
        return paymentService.getPaymentsByCustomerEmail(tenantCode, customerEmail);
    }

    @PutMapping("/{id}/status")
    public PaymentResponse updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status,
            @RequestParam(required = false) String failureReason) {
        log.info("Updating payment status for ID: {} to status: {}", id, status);
        return paymentService.updatePaymentStatus(id, status, failureReason);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        log.info("Deleting payment with ID: {}", id);
        paymentService.deletePayment(id);
    }

    @GetMapping("/exists/payment-id/{paymentId}")
    public Boolean existsByPaymentId(@PathVariable String paymentId) {
        return paymentService.existsByPaymentId(paymentId);
    }

    @GetMapping("/exists/transaction-id/{transactionId}")
    public Boolean existsByTransactionId(@PathVariable String transactionId) {
        return paymentService.existsByTransactionId(transactionId);
    }
}