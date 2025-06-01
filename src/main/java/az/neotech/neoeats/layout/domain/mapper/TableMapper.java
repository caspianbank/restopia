package az.neotech.neoeats.layout.domain.mapper;

import az.neotech.neoeats.layout.domain.dto.request.TableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.TableResponse;
import az.neotech.neoeats.layout.domain.entity.RestaurantTable;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {
    RestaurantTable toEntity(TableRequest request);

    TableResponse toDto(RestaurantTable table);

    List<TableResponse> toDtoList(List<RestaurantTable> tables);
}
