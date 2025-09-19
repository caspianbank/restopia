package az.restopia.commons.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DocumentGenerationStatus;
import az.restopia.commons.domain.enums.DocumentType;
import az.restopia.commons.domain.enums.FileType;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String documentCode;

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
