package az.neotech.neoeats.inventory.domain.enums;

public enum TransactionType {
    PURCHASE,
    SALE,
    RETURN,
    WASTE,
    DISPATCH,  // send to stores
    RECEIVE,   // received from stores
}
