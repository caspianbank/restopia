package az.restopia.tickets.controller;

import az.restopia.tickets.domain.request.TicketRequest;
import az.restopia.tickets.domain.response.TicketResponse;
import az.restopia.tickets.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    
    private final TicketService ticketService;
    
    @GetMapping
    public ResponseEntity<Page<TicketResponse>> getAllTickets(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            Pageable pageable) {
        log.info("GET /api/v1/tickets - tenant: {}", tenantCode);
        Page<TicketResponse> tickets = ticketService.getAllTickets(tenantCode, pageable);
        return ResponseEntity.ok(tickets);
    }
    
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @Valid @RequestBody TicketRequest request) {
        log.info("POST /api/v1/tickets - tenant: {}", tenantCode);
        TicketResponse response = ticketService.createTicket(tenantCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        log.info("GET /api/v1/tickets/{} - tenant: {}", id, tenantCode);
        TicketResponse ticket = ticketService.getTicketById(tenantCode, id);
        return ResponseEntity.ok(ticket);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody TicketRequest request) {
        log.info("PUT /api/v1/tickets/{} - tenant: {}", id, tenantCode);
        TicketResponse response = ticketService.updateTicket(tenantCode, id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        log.info("DELETE /api/v1/tickets/{} - tenant: {}", id, tenantCode);
        ticketService.deleteTicket(tenantCode, id);
        return ResponseEntity.noContent().build();
    }
}