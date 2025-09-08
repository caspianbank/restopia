package az.restopia.customer.repository;

import az.restopia.customer.domain.entity.CustomerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRatingRepository extends JpaRepository<CustomerRating, Long> {
}
