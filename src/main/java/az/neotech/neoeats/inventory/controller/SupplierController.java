package az.neotech.neoeats.inventory.controller;

import az.neotech.neoeats.inventory.domain.request.SupplierRequest;
import az.neotech.neoeats.inventory.domain.response.SupplierResponse;
import az.neotech.neoeats.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: add validation
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService service;

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        return service.getAllSuppliers();
    }

    @PostMapping
    public SupplierResponse createSupplier(@RequestBody SupplierRequest request) {
        return service.createSupplier(request);
    }

    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(@PathVariable Long id, @RequestBody SupplierRequest request) {
        return service.updateSupplier(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        service.deleteSupplier(id);
    }
}
