package az.neotech.neoeats.layout.domain.mapper;

import az.neotech.neoeats.layout.domain.dto.request.AreaRequest;
import az.neotech.neoeats.layout.domain.dto.responce.AreaResponse;
import az.neotech.neoeats.layout.domain.entity.Area;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DiningTableMapper.class)
public interface AreaMapper {
    Area toEntity(AreaRequest request);

    AreaResponse toResponse(Area area);

    List<AreaResponse> toResponseList(List<Area> areas);
}
