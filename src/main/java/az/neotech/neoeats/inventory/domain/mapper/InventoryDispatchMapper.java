package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.InventoryDispatch;
import az.neotech.neoeats.inventory.domain.request.InventoryDispatchRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryDispatchResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventoryDispatchMapper {

    InventoryDispatch toEntity(InventoryDispatchRequest request);

    InventoryDispatchResponse toResponse(InventoryDispatch entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInventoryDispatchFromRequest(InventoryDispatchRequest request, @MappingTarget InventoryDispatch entity);
}