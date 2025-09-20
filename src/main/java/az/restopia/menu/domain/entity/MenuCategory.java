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
@Table(name = "menu_categories")
public class MenuCategory extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuCategoryTranslation> translations = new HashSet<>();
}
