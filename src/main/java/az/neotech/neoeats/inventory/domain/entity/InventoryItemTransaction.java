package az.neotech.neoeats.inventory.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import az.neotech.neoeats.commons.constants.ColumnLengthConstants;
import az.neotech.neoeats.inventory.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tenant_inventory_transactions")
public class InventoryItemTransaction extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 20, nullable = false)
    private TransactionType transactionType;

    @Column(name = "quantity", precision = 12, scale = 3, nullable = false)
    private BigDecimal quantity;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private TenantBusinessStore store;
}
