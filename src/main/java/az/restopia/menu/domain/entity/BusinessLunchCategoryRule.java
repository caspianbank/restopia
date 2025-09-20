package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
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
@Table(name = "business_lunch_category_rules")
public class BusinessLunchCategoryRule extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_lunch_item_id", nullable = false)
    private MenuItem businessLunchItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    private MenuCategory menuCategory;

    @Column(name = "min_selections", nullable = false)
    private Integer minSelections = 1;

    @Column(name = "max_selections", nullable = false)
    private Integer maxSelections = 1;

    @Column(name = "is_required", nullable = false)
    private boolean required = true;

    @Column(name = "position", nullable = false)
    private Integer position = 0;

    @Column(name = "display_name", length = 100)
    private String displayName; // Custom name for this category in the business lunch context

    // Items specifically available for this business lunch category
    @OneToMany(mappedBy = "categoryRule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BusinessLunchCategoryItem> availableItems = new HashSet<>();
}