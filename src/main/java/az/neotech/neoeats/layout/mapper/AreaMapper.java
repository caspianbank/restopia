package az.neotech.neoeats.layout.mapper;

import az.neotech.neoeats.layout.dto.request.AreaRequest;
import az.neotech.neoeats.layout.dto.responce.AreaResponse;
import az.neotech.neoeats.layout.entity.Area;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = TableMapper.class)
public interface AreaMapper {
    Area toEntity(AreaRequest request);

    AreaResponse toDto(Area area);

    List<AreaResponse> toDtoList(List<Area> areas);
}
