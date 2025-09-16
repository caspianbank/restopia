
package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.Inventory;
import az.restopia.inventory.domain.request.InventoryRequest;
import az.restopia.inventory.domain.response.InventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "businessStore", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Inventory toEntity(InventoryRequest request);

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "business.name", target = "businessName")
    @Mapping(source = "businessStore.id", target = "businessStoreId")
    @Mapping(source = "businessStore.name", target = "businessStoreName")
    InventoryResponse toResponse(Inventory entity);

    List<InventoryResponse> toResponseList(List<Inventory> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "businessStore", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateEntity(InventoryRequest request, @MappingTarget Inventory entity);
}