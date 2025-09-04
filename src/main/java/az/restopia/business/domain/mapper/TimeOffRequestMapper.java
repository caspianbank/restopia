package az.restopia.business.domain.mapper;

import az.restopia.business.domain.entity.TimeOffRequestEntity;
import az.restopia.business.domain.request.TimeOffRequest;
import az.restopia.business.domain.response.TimeOffResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TimeOffRequestMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    TimeOffRequestEntity toEntity(TimeOffRequest request);
    
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.fullName", target = "employeeName")
    @Mapping(source = "status", target = "status")
    TimeOffResponse toResponse(TimeOffRequestEntity entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntity(TimeOffRequest request, @MappingTarget TimeOffRequestEntity entity);
}