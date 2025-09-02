package az.neotech.neoeats.business.repository;

import az.neotech.neoeats.business.domain.entity.TenantCustomerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantCustomerRatingRepository extends JpaRepository<TenantCustomerRating, Long> {
}
