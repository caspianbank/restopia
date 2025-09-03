package az.restopia.layout.domain.mapper;

import az.restopia.layout.domain.dto.request.DiningTableRequest;
import az.restopia.layout.domain.dto.responce.DiningTableResponse;
import az.restopia.layout.domain.entity.DiningTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiningTableMapper {
    DiningTable toEntity(DiningTableRequest request);

    DiningTableResponse toResponse(DiningTable table);

    List<DiningTableResponse> toResponseList(List<DiningTable> tables);
}
