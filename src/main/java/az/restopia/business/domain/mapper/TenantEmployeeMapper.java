package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.TenantEmployee;
import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantEmployeeMapper {

    TenantEmployee toEntity(TenantEmployeeRequest request);

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "store.id", target = "storeId")
    TenantEmployeeResponse toResponse(TenantEmployee employee);

    List<TenantEmployeeResponse> toResponseList(List<TenantEmployee> employees);
}
