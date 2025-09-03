package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.SupplierRequest;
import az.restopia.inventory.domain.response.SupplierResponse;
import az.restopia.inventory.service.SupplierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
@Validated
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Page<SupplierResponse>> getAllSuppliers(
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {

        log.debug("Fetching suppliers with pagination: {}", pageable);
        Page<SupplierResponse> suppliers = supplierService.getAllSuppliers(pageable);
        log.info("Retrieved {} suppliers", suppliers.getTotalElements());

        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<SupplierResponse> getSupplierById(
            @PathVariable @Positive Long id) {

        log.debug("Fetching supplier with ID: {}", id);
        SupplierResponse supplier = supplierService.getSupplierById(id);

        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<SupplierResponse> createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        log.info("Creating new supplier: {}", request.name());
        SupplierResponse createdSupplier = supplierService.createSupplier(request);
        log.info("Successfully created supplier with ID: {}", createdSupplier.id());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable @Positive Long id,
            @Valid @RequestBody SupplierRequest request) {

        log.info("Updating supplier with ID: {}", id);
        SupplierResponse updatedSupplier = supplierService.updateSupplier(id, request);
        log.info("Successfully updated supplier with ID: {}", id);

        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSupplier(
            @PathVariable @Positive Long id) {

        log.warn("Deleting supplier with ID: {}", id);
        supplierService.deleteSupplier(id);
        log.info("Successfully deleted supplier with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Page<SupplierResponse>> searchSuppliersByName(
            @RequestParam String name,
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {

        log.debug("Searching suppliers by name: {}", name);
        Page<SupplierResponse> suppliers = supplierService.searchSuppliersByName(name, pageable);

        return ResponseEntity.ok(suppliers);
    }
}