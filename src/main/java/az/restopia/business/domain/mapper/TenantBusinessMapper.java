package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.request.TenantBusinessRequest;
import az.restopia.business.domain.response.TenantBusinessResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TenantBusinessMapper {

    TenantBusiness toEntity(TenantBusinessRequest request);

    TenantBusinessResponse toResponse(TenantBusiness entity);

    void updateEntityFromRequest(TenantBusinessRequest request, @MappingTarget TenantBusiness entity);
}
