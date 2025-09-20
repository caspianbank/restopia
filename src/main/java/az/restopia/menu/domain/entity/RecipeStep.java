package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_steps")
public class RecipeStep extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private MenuItemRecipe recipe;

    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @Column(name = "step_title", length = 200)
    private String stepTitle;

    @Column(name = "instruction", nullable = false, length = 2000)
    private String instruction;

    @Column(name = "time_minutes")
    private Integer timeMinutes; // Time for this specific step

    @Column(name = "temperature")
    private Integer temperature; // Cooking temperature if applicable

    @Column(name = "is_public", nullable = false)
    private boolean publiclyVisible = false;

    @Column(name = "chef_tip", length = 500)
    private String chefTip; // Internal tip for kitchen staff

    @Column(name = "step_image_url")
    private String stepImageUrl;
}