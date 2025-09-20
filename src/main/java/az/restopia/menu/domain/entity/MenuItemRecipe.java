package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.menu.domain.enums.DifficultyLevel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_recipes")
public class MenuItemRecipe extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "recipe_name", nullable = false, length = 200)
    private String recipeName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "prep_time_minutes")
    private Integer prepTimeMinutes;

    @Column(name = "cook_time_minutes")
    private Integer cookTimeMinutes;

    @Column(name = "total_time_minutes")
    private Integer totalTimeMinutes;

    @Column(name = "servings", nullable = false)
    private Integer servings = 1;

    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    @Column(name = "is_public", nullable = false)
    private boolean publiclyVisible = false;

    @Column(name = "chef_notes", length = 2000)
    private String chefNotes; // Internal notes for kitchen staff

    @Column(name = "public_notes", length = 2000)
    private String publicNotes; // Notes visible to customers

    @Column(name = "allergen_info", length = 500)
    private String allergenInfo;

    @Column(name = "dietary_tags", length = 300)
    private String dietaryTags; // e.g., "vegetarian,gluten-free,dairy-free"

    @Column(name = "nutrition_calories")
    private Integer nutritionCalories;

    @Column(name = "recipe_image_url")
    private String recipeImageUrl;

    @Column(name = "recipe_video_url")
    private String recipeVideoUrl;

    // todo: followings are not necessary for now
//    // Recipe steps
//    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("stepNumber ASC")
//    private List<RecipeStep> steps = new ArrayList<>();
//
//    // Recipe ingredients (different from menu item components - this is for recipe display)
//    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("displayOrder ASC")
//    private List<RecipeIngredient> ingredients = new ArrayList<>();
}