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
@Table(name = "menu_item_choice_groups")
public class MenuItemChoiceGroup extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "min_choices", nullable = false)
    private Integer minChoices = 0;

    @Column(name = "max_choices", nullable = false)
    private Integer maxChoices = 1;

    @OneToMany(mappedBy = "choiceGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItem> choices = new HashSet<>();
}