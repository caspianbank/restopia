package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.domain.request.TenantBusinessStoreRequest;
import az.restopia.business.domain.response.TenantBusinessStoreResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = MapperHelper.class)
public interface TenantBusinessStoreMapper {
    @Mapping(target = "businessId", source = "business.id")
    @Mapping(target = "openingHours", source = "store.openingHours", qualifiedByName = "jsonToMap")
    @Mapping(target = "description", source = "business.description")
    @Mapping(target = "email", source = "business.email")
    @Mapping(target = "phone", source = "business.phone")
    @Mapping(target = "socials", source = "business.socials", qualifiedByName = "jsonToMap")
    TenantBusinessStoreResponse toResponse(TenantBusinessStore store, TenantBusiness business);

    @Mapping(target = "business", ignore = true)
    TenantBusinessStore toEntity(TenantBusinessStoreRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(TenantBusinessStoreRequest request, @MappingTarget TenantBusinessStore store);
}
