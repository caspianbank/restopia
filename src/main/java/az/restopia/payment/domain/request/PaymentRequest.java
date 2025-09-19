package az.restopia.payment.domain.request;

import az.neotech.commons.finance.Currency;
import az.restopia.payment.domain.enums.PaymentMethod;
import az.restopia.payment.domain.enums.PaymentProvider;
import az.restopia.payment.domain.enums.PaymentStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    @NotBlank(message = "Tenant code is required")
    @Size(max = 10, message = "Tenant code must not exceed 10 characters")
    private String tenantCode;

    @Size(max = 255, message = "Transaction ID must not exceed 255 characters")
    private String transactionId;

    @Size(max = 255, message = "Reference number must not exceed 255 characters")
    private String referenceNumber;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Currency is required")
    private Currency currency;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment provider is required")
    private PaymentProvider paymentProvider;

    @NotNull(message = "Status is required")
    private PaymentStatus status;

    @Size(max = 500, message = "Failure reason must not exceed 500 characters")
    private String failureReason;

    @Size(max = 255, message = "Customer name must not exceed 255 characters")
    private String customerName;

    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Customer email must not exceed 255 characters")
    private String customerEmail;

    @Size(max = 20, message = "Customer phone must not exceed 20 characters")
    private String customerPhone;

    private LocalDateTime processedAt;

    @NotNull(message = "Order ID is required")
    private String orderId;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;
}
