package az.neotech.neoeats.layout.repository;

import az.neotech.neoeats.layout.domain.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {
    Optional<DiningTable> findByCodeIgnoreCase(String code);

    Optional<DiningTable> findByQrCode(String qrCode);

    List<DiningTable> findAllByTenantCode(String tenantCode);
}
