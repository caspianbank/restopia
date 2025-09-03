package az.restopia.business.repository;

import az.restopia.business.domain.entity.TenantCustomerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantCustomerRatingRepository extends JpaRepository<TenantCustomerRating, Long> {
}
