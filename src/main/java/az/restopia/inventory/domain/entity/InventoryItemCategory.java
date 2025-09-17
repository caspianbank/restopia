package az.restopia.inventory.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_inventory_item_categories")
public class InventoryItemCategory extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private InventoryItemCategory parent;

    @Column(name = "sort_order")
    private Integer sortOrder = 0; // todo: during sorts nulls must come last
}
