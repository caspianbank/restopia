package az.neotech.neoeats.inventory.service;

import az.neotech.neoeats.inventory.domain.request.SupplierRequest;
import az.neotech.neoeats.inventory.domain.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    List<SupplierResponse> getAllSuppliers();

    void deleteSupplier(Long id);
}
