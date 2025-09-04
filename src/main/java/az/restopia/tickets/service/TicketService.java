package az.restopia.tickets.service;

import az.restopia.tickets.domain.request.TicketRequest;
import az.restopia.tickets.domain.response.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {
    
    Page<TicketResponse> getAllTickets(String tenantCode, Pageable pageable);
    
    TicketResponse createTicket(String tenantCode, TicketRequest request);
    
    TicketResponse getTicketById(String tenantCode, Long id);
    
    TicketResponse updateTicket(String tenantCode, Long id, TicketRequest request);
    
    void deleteTicket(String tenantCode, Long id);
}