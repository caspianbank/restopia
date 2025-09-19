package az.restopia.tickets.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.tickets.domain.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    Page<Ticket> findByTenantCodeAndDeleteStatus(
        String tenantCode, 
        DeleteStatus deleteStatus, 
        Pageable pageable
    );
    
    Optional<Ticket> findByIdAndTenantCodeAndDeleteStatus(
        Long id, 
        String tenantCode, 
        DeleteStatus deleteStatus
    );
}