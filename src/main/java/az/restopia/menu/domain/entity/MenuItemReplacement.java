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
@Table(name = "menu_item_replacements")
public class MenuItemReplacement extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "replacement_group_name", nullable = false, length = 100)
    private String replacementGroupName; // Must match the replacementGroupName in MenuItemComponent

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replacement_menu_item_id", nullable = false)
    private MenuItem replacementMenuItem;

    @Column(name = "additional_price", precision = 10, scale = 2)
    private BigDecimal additionalPrice = BigDecimal.ZERO; // Price difference from original

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "position", nullable = false)
    private Integer position = 0;
}