package az.restopia.business.domain.entity;

import az.restopia.business.domain.enums.RatingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenant_customer_ratings")
public class TenantCustomerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tenant who owns the business
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_business_id", nullable = false)
    private TenantBusiness tenantBusiness;

    // Optional customer (nullable if anonymous)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_customer_id")
    private TenantCustomer tenantCustomer;

    @Enumerated(EnumType.STRING)
    private RatingType ratingCategory;

    // Value (e.g. 1-5 stars)
    @Column(nullable = false)
    private Integer value;

    // Optional text feedback
    @Column(length = 500)
    private String comment;
}

