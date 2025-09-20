package az.restopia.menu.domain.entity;

import az.neotech.commons.Language;
import az.neotech.commons.audit.DetailedAudit;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_translations")
public class MenuItemTranslation extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "language_code", nullable = false, length = 5)
    private Language language;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "tags", length = 500)
    private String tags; // todo: tags that are separated with comma
}