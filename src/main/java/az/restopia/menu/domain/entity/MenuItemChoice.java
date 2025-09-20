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
@Table(name = "menu_item_choices")
public class MenuItemChoice extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_group_id", nullable = false)
    private MenuItemChoiceGroup choiceGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_menu_item_id", nullable = false)
    private MenuItem choiceMenuItem;

    @Column(name = "additional_price", precision = 10, scale = 2)
    private BigDecimal additionalPrice = BigDecimal.ZERO;

    @Column(name = "is_default", nullable = false)
    private boolean defaultChoice = false;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}