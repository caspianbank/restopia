package az.neotech.neoeats.commons.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.commons.domain.constants.ColumnLengthConstants;
import az.neotech.neoeats.commons.domain.enums.DocumentGenerationStatus;
import az.neotech.neoeats.commons.domain.enums.DocumentType;
import az.neotech.neoeats.commons.domain.enums.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "generated_documents")
public class Document extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileType fileType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentGenerationStatus documentGenerationStatus;
}
