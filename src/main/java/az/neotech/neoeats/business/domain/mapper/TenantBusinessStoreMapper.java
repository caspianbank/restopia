package az.neotech.neoeats.business.domain.mapper;

import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import az.neotech.neoeats.business.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessStoreResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TenantBusinessStoreMapper {
    @Mapping(source = "business.id", target = "businessId")
    TenantBusinessStoreResponse toResponse(TenantBusinessStore store);

    @Mapping(target = "business", ignore = true)
    TenantBusinessStore toEntity(TenantBusinessStoreRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(TenantBusinessStoreRequest request, @MappingTarget TenantBusinessStore store);
}
