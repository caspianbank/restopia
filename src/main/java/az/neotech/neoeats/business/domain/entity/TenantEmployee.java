package az.neotech.neoeats.business.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.neotech.neoeats.commons.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_employee")
public class TenantEmployee extends DateAudit {
    //todo: consider other details such as pin code, identity number in future as well
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = 50)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private TenantBusiness business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private TenantBusinessStore store;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
