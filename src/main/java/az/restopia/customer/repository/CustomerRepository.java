package az.restopia.customer.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.customer.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumberAndTenantCode(String phone, String tenantCode);

    Page<Customer> findByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);

    Optional<Customer> findByIdAndDeleteStatus(Long id, DeleteStatus deleteStatus);

    @Query("SELECT c FROM Customer c WHERE " +
            "c.deleteStatus = :deleteStatus AND " +
            "(:fullName IS NULL OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) AND " +
            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:phoneNumber IS NULL OR c.phoneNumber LIKE CONCAT('%', :phoneNumber, '%'))")
    Page<Customer> searchCustomers(@Param("fullName") String fullName,
                                   @Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("email") String email,
                                   @Param("phoneNumber") String phoneNumber,
                                   @Param("deleteStatus") DeleteStatus deleteStatus,
                                   Pageable pageable);
}
