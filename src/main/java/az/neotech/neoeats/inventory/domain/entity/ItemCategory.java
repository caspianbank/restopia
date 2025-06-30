package az.neotech.neoeats.inventory.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.neotech.neoeats.commons.domain.constants.ColumnLengthConstants;
import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenant_item_categories")
public class ItemCategory extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ItemCategory parent;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "lang", length = 10, nullable = false)
    private String lang = "EN"; // todo: replace it with enum from common lib

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
