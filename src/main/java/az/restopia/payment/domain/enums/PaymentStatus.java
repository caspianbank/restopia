package az.restopia.payment.domain.enums;

public enum PaymentStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    CANCELLED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    EXPIRED,
    REQUIRES_ACTION,
    DISPUTED
}
