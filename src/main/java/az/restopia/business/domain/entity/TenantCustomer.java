package az.restopia.business.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tenant_customers")
public class TenantCustomer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    private String fullName;

    private String firstName;

    private String lastName;

    private String countryCode;

    private String phoneNumber;

    private String email;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}