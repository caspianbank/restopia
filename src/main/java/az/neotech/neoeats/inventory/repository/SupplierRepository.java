package az.neotech.neoeats.inventory.repository;

import az.neotech.neoeats.inventory.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAll();
    Optional<Supplier> findById(Long id);
}
