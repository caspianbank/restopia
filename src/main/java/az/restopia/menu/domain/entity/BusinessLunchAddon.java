package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_lunch_addons")
public class BusinessLunchAddon extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_lunch_item_id", nullable = false)
    private MenuItem businessLunchItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_menu_item_id", nullable = false)
    private MenuItem addonMenuItem;

    @Column(name = "addon_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal addonPrice;

    @Column(name = "addon_group", length = 50)
    private String addonGroup; // e.g., "Desserts", "Extra Drinks"

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "position", nullable = false)
    private Integer position = 0;

    @Column(name = "max_quantity", nullable = false)
    private Integer maxQuantity = 1;
}