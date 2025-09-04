package az.restopia.tickets.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.tickets.domain.entity.Ticket;
import az.restopia.tickets.domain.mapper.TicketMapper;
import az.restopia.tickets.domain.request.TicketRequest;
import az.restopia.tickets.domain.response.TicketResponse;
import az.restopia.tickets.repository.TicketRepository;
import az.restopia.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {
    
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    
    @Override
    public Page<TicketResponse> getAllTickets(String tenantCode, Pageable pageable) {
        log.info("Getting all tickets for tenant: {}", tenantCode);
        Page<Ticket> tickets = ticketRepository.findByTenantCodeAndDeleteStatus(
            tenantCode, DeleteStatus.ACTIVE, pageable
        );
        return tickets.map(ticketMapper::toResponse);
    }
    
    @Override
    @Transactional
    public TicketResponse createTicket(String tenantCode, TicketRequest request) {
        log.info("Creating new ticket for tenant: {}", tenantCode);
        Ticket ticket = ticketMapper.toEntity(request);
        ticket.setTenantCode(tenantCode);
        ticket.setDeleteStatus(DeleteStatus.ACTIVE);
        
        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Created ticket with id: {} for tenant: {}", savedTicket.getId(), tenantCode);
        return ticketMapper.toResponse(savedTicket);
    }
    
    @Override
    public TicketResponse getTicketById(String tenantCode, Long id) {
        log.info("Getting ticket by id: {} for tenant: {}", id, tenantCode);
        Ticket ticket = ticketRepository.findByIdAndTenantCodeAndDeleteStatus(
            id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Ticket not found with id: " + id));
        
        return ticketMapper.toResponse(ticket);
    }
    
    @Override
    @Transactional
    public TicketResponse updateTicket(String tenantCode, Long id, TicketRequest request) {
        log.info("Updating ticket with id: {} for tenant: {}", id, tenantCode);
        Ticket ticket = ticketRepository.findByIdAndTenantCodeAndDeleteStatus(
            id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Ticket not found with id: " + id));
        
        ticketMapper.updateEntity(request, ticket);
        Ticket savedTicket = ticketRepository.save(ticket);
        
        log.info("Updated ticket with id: {} for tenant: {}", id, tenantCode);
        return ticketMapper.toResponse(savedTicket);
    }
    
    @Override
    @Transactional
    public void deleteTicket(String tenantCode, Long id) {
        log.info("Deleting ticket with id: {} for tenant: {}", id, tenantCode);
        Ticket ticket = ticketRepository.findByIdAndTenantCodeAndDeleteStatus(
            id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Ticket not found with id: " + id));
        
        ticket.setDeleteStatus(DeleteStatus.DELETED);
        ticketRepository.save(ticket);
        log.info("Deleted ticket with id: {} for tenant: {}", id, tenantCode);
    }
}