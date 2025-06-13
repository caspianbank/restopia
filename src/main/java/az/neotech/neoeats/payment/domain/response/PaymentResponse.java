package az.neotech.neoeats.payment.domain.response;

import az.neotech.neoeats.commons.enums.Currency;
import az.neotech.neoeats.payment.domain.enums.PaymentMethod;
import az.neotech.neoeats.payment.domain.enums.PaymentProvider;
import az.neotech.neoeats.payment.domain.enums.PaymentStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private Long id;
    private String tenantCode;
    private String paymentId;
    private String transactionId;
    private String referenceNumber;
    private BigDecimal amount;
    private Currency currency;
    private PaymentMethod paymentMethod;
    private PaymentProvider paymentProvider;
    private PaymentStatus status;
    private String failureReason;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime processedAt;
    private Long orderId;
    private String description;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
