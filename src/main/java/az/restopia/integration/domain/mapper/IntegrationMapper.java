package az.restopia.integration.domain.mapper;

import az.restopia.integration.domain.entity.Integration;
import az.restopia.integration.domain.entity.TenantIntegration;
import az.restopia.integration.domain.request.TenantIntegrationRequest;
import az.restopia.integration.domain.response.IntegrationResponse;
import az.restopia.integration.domain.response.TenantIntegrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IntegrationMapper {

    IntegrationResponse toResponse(Integration integration);

    TenantIntegration toEntity(TenantIntegrationRequest request);

    TenantIntegrationResponse toResponse(TenantIntegration entity);

    void updateEntityFromRequest(TenantIntegrationRequest request, @MappingTarget TenantIntegration entity);

    IntegrationResponse toIntegrationResponse(Integration integration);
}
