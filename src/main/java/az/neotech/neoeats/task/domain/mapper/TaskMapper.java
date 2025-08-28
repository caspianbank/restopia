package az.neotech.neoeats.task.domain.mapper;

import az.neotech.neoeats.task.domain.entity.Task;
import az.neotech.neoeats.task.domain.request.TaskRequest;
import az.neotech.neoeats.task.domain.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    /**
     * Convert TaskRequest to Task entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    Task toEntity(TaskRequest request);

    /**
     * Convert Task entity to TaskResponse
     */
    TaskResponse toResponse(Task task);

    /**
     * Update existing Task entity with data from TaskRequest
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    void update(@MappingTarget Task task, TaskRequest request);

    /**
     * Convert Task entity to TaskResponse with custom mapping for audit fields
     */
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    TaskResponse toResponseWithAudit(Task task);
}