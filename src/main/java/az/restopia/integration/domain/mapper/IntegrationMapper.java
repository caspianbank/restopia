package az.restopia.integration.domain.mapper;

import az.restopia.integration.domain.entity.Integration;
import az.restopia.integration.domain.response.IntegrationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntegrationMapper {

    IntegrationResponse toResponse(Integration integration);
}
