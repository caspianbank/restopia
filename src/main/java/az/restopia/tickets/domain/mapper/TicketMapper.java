package az.restopia.tickets.domain.mapper;

import az.restopia.tickets.domain.entity.Ticket;
import az.restopia.tickets.domain.request.TicketRequest;
import az.restopia.tickets.domain.response.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Ticket toEntity(TicketRequest request);
    
    TicketResponse toResponse(Ticket ticket);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntity(TicketRequest request, @MappingTarget Ticket ticket);
}