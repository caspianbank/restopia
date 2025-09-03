package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.SupplierRequest;
import az.restopia.inventory.domain.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    Page<SupplierResponse> getAllSuppliers(Pageable pageable);

    SupplierResponse getSupplierById(Long id);

    void deleteSupplier(Long id);

    Page<SupplierResponse> searchSuppliersByName(String name, Pageable pageable);
}