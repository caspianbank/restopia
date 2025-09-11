package az.restopia.integration.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.integration.domain.enums.IntegrationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "integrations")
public class Integration extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private IntegrationType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "supported_countries", columnDefinition = "jsonb")
    private List<String> supportedCountries;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "capabilities", columnDefinition = "jsonb")
    private Map<String, Object> capabilities;

    @OneToMany(mappedBy = "integration", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TenantIntegration> tenantIntegrations;
}
