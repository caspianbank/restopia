package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.InventoryDispatch;
import az.restopia.inventory.domain.request.InventoryDispatchRequest;
import az.restopia.inventory.domain.response.InventoryDispatchResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InventoryDispatchMapper {

    InventoryDispatch toEntity(InventoryDispatchRequest request);

    InventoryDispatchResponse toResponse(InventoryDispatch entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInventoryDispatchFromRequest(InventoryDispatchRequest request, @MappingTarget InventoryDispatch entity);
}