package az.neotech.neoeats.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import az.neotech.neoeats.domain.entity.TenantBusiness;
import az.neotech.neoeats.domain.request.TenantBusinessRequest;
import az.neotech.neoeats.domain.response.TenantBusinessResponse;

@Mapper(componentModel = "spring")
public interface TenantBusinessMapper {

    TenantBusiness toEntity(TenantBusinessRequest request);

    TenantBusinessResponse toResponse(TenantBusiness entity);

    void updateEntityFromRequest(TenantBusinessRequest request, @MappingTarget TenantBusiness entity);
}
