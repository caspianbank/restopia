package az.neotech.neoeats.inventory.domain.enums;

public enum PurchaseOrderStatus {
    PENDING,        // Just created, awaiting approval
    APPROVED,       // Approved by manager/supervisor
    REJECTED,       // Rejected during approval process
    PARTIALLY_RECEIVED, // Only some items have been received
    RECEIVED,       // All items have been received
    CANCELLED       // Cancelled before fulfillment
}
