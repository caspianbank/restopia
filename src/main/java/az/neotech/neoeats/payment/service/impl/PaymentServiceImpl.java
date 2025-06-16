package az.neotech.neoeats.payment.service.impl;

import az.neotech.neoeats.payment.service.PaymentService;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.order.repository.OrderRepository;
import az.neotech.neoeats.payment.domain.entity.Payment;
import az.neotech.neoeats.payment.domain.enums.PaymentStatus;
import az.neotech.neoeats.payment.domain.mapper.PaymentMapper;
import az.neotech.neoeats.payment.domain.request.PaymentRequest;
import az.neotech.neoeats.payment.domain.response.PaymentResponse;
import az.neotech.neoeats.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// todo: add logging statements for all methods
// todo: check delete method for soft delete
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        log.info("Creating payment for order ID: {}", request.getOrderId());

        Payment payment = paymentMapper.toPaymentEntity(request);

        if (payment.getProcessedAt() == null) {
            payment.setProcessedAt(LocalDateTime.now());
        }

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment created successfully with ID: {} and Payment ID: {}",
                savedPayment.getId(), savedPayment.getPaymentId());

        return paymentMapper.mapToResponse(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(Long id) {
        log.debug("Fetching payment by ID: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found with ID: " + id));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByPaymentId(String paymentId) {
        log.debug("Fetching payment by payment ID: {}", paymentId);
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found with payment ID: " + paymentId));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByTransactionId(String transactionId) {
        log.debug("Fetching payment by transaction ID: {}", transactionId);
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found with transaction ID: " + transactionId));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByReferenceNumber(String referenceNumber) {
        log.debug("Fetching payment by reference number: {}", referenceNumber);
        Payment payment = paymentRepository.findByReferenceNumber(referenceNumber)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found with reference number: " + referenceNumber));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        log.debug("Fetching payment by order ID: {}", orderId);
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found for order ID: " + orderId));
        return paymentMapper.mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByTenantCode(String tenantCode) {
        log.debug("Fetching payments for tenant: {}", tenantCode);
        List<Payment> payments = paymentRepository.findByTenantCodeOrderByCreatedAtDesc(tenantCode);
        return payments.stream()
                .map(paymentMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByTenantCodeAndStatus(String tenantCode, PaymentStatus status) {
        log.debug("Fetching payments for tenant: {} with status: {}", tenantCode, status);
        List<Payment> payments = paymentRepository.findByTenantCodeAndStatus(tenantCode, status);
        return payments.stream()
                .map(paymentMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByCustomerEmail(String tenantCode, String customerEmail) {
        log.debug("Fetching payments for tenant: {} and customer email: {}", tenantCode, customerEmail);
        List<Payment> payments = paymentRepository.findByTenantCodeAndCustomerEmail(tenantCode, customerEmail);
        return payments.stream()
                .map(paymentMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentResponse updatePaymentStatus(Long id, PaymentStatus status, String failureReason) {
        log.info("Updating payment status for ID: {} to status: {}", id, status);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Payment not found with ID: " + id));

        payment.setStatus(status);
        payment.setFailureReason(failureReason);
        payment.setProcessedAt(LocalDateTime.now());

        Payment updatedPayment = paymentRepository.save(payment);
        log.info("Payment status updated successfully for ID: {}", id);

        return paymentMapper.mapToResponse(updatedPayment);
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        log.info("Deleting payment with ID: {}", id);
        if (!paymentRepository.existsById(id)) {
            throw new RecordNotFoundException("Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
        log.info("Payment deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentId(paymentId).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId).isPresent();
    }
}

