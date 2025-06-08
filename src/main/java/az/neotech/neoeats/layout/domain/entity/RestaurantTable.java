package az.neotech.neoeats.layout.domain.entity;


import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import az.neotech.neoeats.menu.domain.entity.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restaurant_table")
public class RestaurantTable extends DetailedAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status = TableStatus.AVAILABLE;

    private int posX, posY;

    @Column(name = "qr_code", unique = true, nullable = false, length = 100)
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
