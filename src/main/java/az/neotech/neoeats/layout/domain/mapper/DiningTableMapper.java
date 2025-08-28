package az.neotech.neoeats.layout.domain.mapper;

import az.neotech.neoeats.layout.domain.dto.request.DiningTableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.DiningTableResponse;
import az.neotech.neoeats.layout.domain.entity.DiningTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiningTableMapper {
    DiningTable toEntity(DiningTableRequest request);

    DiningTableResponse toResponse(DiningTable table);

    List<DiningTableResponse> toResponseList(List<DiningTable> tables);
}
