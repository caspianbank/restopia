package az.restopia.payment.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.neotech.commons.finance.Currency;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.order.domain.entity.Order;
import az.restopia.payment.domain.enums.PaymentMethod;
import az.restopia.payment.domain.enums.PaymentProvider;
import az.restopia.payment.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_customer_payments")
public class Payment extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Column(name = "payment_id", unique = true, nullable = false)
    private String paymentId;

    /**
     * Unique identifier for the transaction, typically provided by the payment gateway.
     */
    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    /**
     * Customers can use the reference number for the payment for tracking or assitance.
     */
    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 4)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_provider", nullable = false)
    private PaymentProvider paymentProvider;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    /**
     * The date and time when the payment was initiated.
     */
    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (paymentId == null) {
            paymentId = generatePaymentId();
        }
    }

    private String generatePaymentId() {
        return "PAY_" + System.currentTimeMillis() + "_"
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

