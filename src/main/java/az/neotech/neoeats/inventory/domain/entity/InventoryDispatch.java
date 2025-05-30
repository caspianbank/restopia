package az.neotech.neoeats.inventory.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "inventory_dispatches")
public class InventoryDispatch extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = 50)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_store_id", nullable = false)
    private TenantBusinessStore toStore;

    @Column(name = "dispatch_date", nullable = false)
    private LocalDateTime dispatchDate;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "dispatch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryDispatchItem> items = new HashSet<>();
}
