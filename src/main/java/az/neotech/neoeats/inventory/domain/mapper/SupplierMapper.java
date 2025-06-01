package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.Supplier;
import az.neotech.neoeats.inventory.domain.request.SupplierRequest;
import az.neotech.neoeats.inventory.domain.response.SupplierResponse;
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