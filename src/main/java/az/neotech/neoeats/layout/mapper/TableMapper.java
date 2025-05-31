package az.neotech.neoeats.layout.mapper;

import az.neotech.neoeats.layout.dto.request.TableRequest;
import az.neotech.neoeats.layout.dto.responce.TableResponse;
import az.neotech.neoeats.layout.entity.RestaurantTable;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {
    RestaurantTable toEntity(TableRequest request);

    TableResponse toDto(RestaurantTable table);

    List<TableResponse> toDtoList(List<RestaurantTable> tables);
}
