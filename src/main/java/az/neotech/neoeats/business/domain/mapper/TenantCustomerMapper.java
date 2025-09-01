package az.neotech.neoeats.business.domain.mapper;

import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import az.neotech.neoeats.business.domain.request.TenantCustomerRequest;
import az.neotech.neoeats.business.domain.response.TenantCustomerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TenantCustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    TenantCustomer toEntity(TenantCustomerRequest request);

    TenantCustomerResponse toResponse(TenantCustomer customer);
}