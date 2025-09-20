package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.menu.domain.enums.RestrictionType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_choice_restrictions")
public class MenuItemChoiceRestriction extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restricting_group_id", nullable = false)
    private MenuItemChoiceGroup restrictingGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restricting_choice_id", nullable = false)
    private MenuItemChoice restrictingChoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restricted_group_id", nullable = false)
    private MenuItemChoiceGroup restrictedGroup;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "restriction_type", nullable = false)
    private RestrictionType restrictionType;
}