package az.restopia.inventory.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.inventory.domain.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findByDeleteStatusNot(DeleteStatus deleteStatus, Pageable pageable);

    Optional<Supplier> findByIdAndDeleteStatusNot(Long id, DeleteStatus deleteStatus);

    Page<Supplier> findByNameContainingIgnoreCaseAndDeleteStatusNot(String name, DeleteStatus deleteStatus, Pageable pageable);

    Page<Supplier> findByAddressContainingIgnoreCaseAndDeleteStatusNot(String address, DeleteStatus deleteStatus, Pageable pageable);

    boolean existsByIdAndDeleteStatusNot(Long id, DeleteStatus deleteStatus);

    boolean existsByEmailAndDeleteStatusNot(String email, DeleteStatus deleteStatus);

    boolean existsByPhonesAndDeleteStatusNot(String phones, DeleteStatus deleteStatus);

    boolean existsByEmailAndDeleteStatusNotAndIdNot(String email, DeleteStatus deleteStatus, Long id);

    boolean existsByPhonesAndDeleteStatusNotAndIdNot(String phones, DeleteStatus deleteStatus, Long id);

    long countByDeleteStatusNot(DeleteStatus deleteStatus);

    @Query("SELECT s FROM Supplier s WHERE s.tenantCode = :tenantCode AND s.deleteStatus != :deleteStatus")
    Page<Supplier> findByTenantCodeAndDeleteStatusNot(@Param("tenantCode") String tenantCode,
                                                      @Param("deleteStatus") DeleteStatus deleteStatus,
                                                      Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE s.email = :email AND s.tenantCode = :tenantCode AND s.deleteStatus != :deleteStatus")
    Optional<Supplier> findByEmailAndTenantCodeAndDeleteStatusNot(@Param("email") String email,
                                                                  @Param("tenantCode") String tenantCode,
                                                                  @Param("deleteStatus") DeleteStatus deleteStatus);

    @Query("SELECT COUNT(s) FROM Supplier s WHERE s.tenantCode = :tenantCode AND s.deleteStatus != :deleteStatus")
    long countByTenantCodeAndDeleteStatusNot(@Param("tenantCode") String tenantCode,
                                             @Param("deleteStatus") DeleteStatus deleteStatus);

    @Query("SELECT s FROM Supplier s WHERE s.tenantCode = :tenantCode AND s.deleteStatus != :deleteStatus")
    List<Supplier> findByTenantCodeAndDeleteStatusNot(@Param("tenantCode") String tenantCode,
                                                      @Param("deleteStatus") DeleteStatus deleteStatus);
}
