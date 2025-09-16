package az.restopia.inventory.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_inventories")
public class Inventory extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private TenantBusiness business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private TenantBusinessStore businessStore;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "location", length = 200)
    private String location;

    /**
     * Main inventory flag:
     * - If store_id IS NULL: Only one business-level inventory can have main=true
     * - If store_id IS NOT NULL: Only one inventory per store can have main=true
     * - Main inventories are used as primary sources/destinations for transfers
     */
    @Column(name = "is_main", nullable = false)
    private boolean main = false;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;

    /**
     * Check if this is a business-level inventory
     */
    public boolean isBusinessLevel() {
        return businessStore == null;
    }

    /**
     * Check if this is a store-level inventory
     */
    public boolean isStoreLevel() {
        return businessStore != null;
    }
}
