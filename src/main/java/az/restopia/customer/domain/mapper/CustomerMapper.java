package az.restopia.customer.domain.mapper;

import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.request.CustomerRequest;
import az.restopia.customer.domain.response.CustomerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CustomerRequest request, @MappingTarget Customer customer);
}