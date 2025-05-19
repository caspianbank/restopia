package az.neotech.neoeats.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import az.neotech.neoeats.domain.entity.Tenant;
import az.neotech.neoeats.domain.request.TenantRequest;
import az.neotech.neoeats.domain.response.TenantResponse;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    Tenant toEntity(TenantRequest request);

    TenantResponse toResponse(Tenant tenant);
}
