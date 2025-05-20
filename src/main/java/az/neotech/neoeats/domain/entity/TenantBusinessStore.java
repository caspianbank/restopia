package az.neotech.neoeats.domain.entity;

import az.neotech.neoeats.domain.enums.DeleteStatus;
import az.neotech.neoeats.domain.enums.StoreType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenant_business_stores")
public class TenantBusinessStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_business_id", nullable = false)
    private TenantBusiness business;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "latitude", precision = 10, scale = 8)
    private Double latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private Double longitude;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_type", length = 50, nullable = false)
    private StoreType storeType = StoreType.PHYSICAL;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "opening_hours", columnDefinition = "jsonb")
    private String openingHours;

    @Column(name = "configurations", columnDefinition = "jsonb", nullable = false)
    private String configurations = "{}";

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
