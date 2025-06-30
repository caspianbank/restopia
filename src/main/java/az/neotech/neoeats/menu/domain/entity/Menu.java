package az.neotech.neoeats.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.commons.domain.constants.ColumnLengthConstants;
import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menus")
public class Menu extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuCategory> categories = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}