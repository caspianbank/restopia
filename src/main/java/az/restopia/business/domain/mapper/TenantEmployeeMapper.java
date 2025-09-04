package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.TenantEmployee;
import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TenantEmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    TenantEmployee toEntity(TenantEmployeeRequest request);

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "business.name", target = "businessName")
    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "store.name", target = "storeName")
    @Mapping(expression = "java(employee.getFullName())", target = "fullName")
    TenantEmployeeResponse toResponse(TenantEmployee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntity(TenantEmployeeRequest request, @MappingTarget TenantEmployee employee);
}
