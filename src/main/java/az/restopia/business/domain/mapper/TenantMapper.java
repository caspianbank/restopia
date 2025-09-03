package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.Tenant;
import az.restopia.business.domain.request.TenantRequest;
import az.restopia.business.domain.response.TenantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    Tenant toEntity(TenantRequest request);

    TenantResponse toResponse(Tenant tenant);
}
