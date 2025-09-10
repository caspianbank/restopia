package az.restopia.order.repository;

import az.restopia.order.domain.entity.BusinessWoltVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessWoltVenueRepository extends JpaRepository<BusinessWoltVenue, Long> {
    Optional<BusinessWoltVenue> findByVenueId(String venueId);
}
