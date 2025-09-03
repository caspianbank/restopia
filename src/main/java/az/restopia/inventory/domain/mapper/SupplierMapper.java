package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.Supplier;
import az.restopia.inventory.domain.request.SupplierRequest;
import az.restopia.inventory.domain.response.SupplierResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toEntity(SupplierRequest request);

    SupplierResponse toResponse(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Supplier supplier, SupplierRequest request);
}