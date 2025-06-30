package az.neotech.neoeats.inventory.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.commons.domain.constants.ColumnLengthConstants;
import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.inventory.domain.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tenant_purchase_orders")
public class PurchaseOrder extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    private PurchaseOrderStatus status;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderItem> items = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
