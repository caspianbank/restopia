package az.neotech.neoeats.layout.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.commons.domain.constants.ColumnLengthConstants;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dining_tables")
public class DiningTable extends DetailedAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    private String code;

    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_status")
    private TableStatus status = TableStatus.AVAILABLE;

    private int posX;

    private int posY;

    private String description;

    @Column(name = "qr_code", unique = true, nullable = false, length = 100)
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
}
