package az.restopia.order.domain.entity;

import az.neotech.commons.Language;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "service_action_prompts")
public class ServiceActionPrompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String promptCode;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    // Translations: key = language code (e.g., "en", "az", "ru")
    @ElementCollection
    @CollectionTable(name = "service_action_prompt_translations", joinColumns = @JoinColumn(name = "prompt_id"))
    @MapKeyColumn(name = "language")
    @Column(name = "text")
    private Map<Language, String> translations;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;

    public String getPromptValue(Language language) {
        return getTranslations().get(language);
    }
}