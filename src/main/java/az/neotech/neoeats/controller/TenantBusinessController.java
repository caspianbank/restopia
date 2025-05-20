package az.neotech.neoeats.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.neotech.neoeats.domain.request.TenantBusinessRequest;
import az.neotech.neoeats.domain.response.TenantBusinessResponse;
import az.neotech.neoeats.service.TenantBusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenants/{code}/businesses")
public class TenantBusinessController {

    private final TenantBusinessService tenantBusinessService;

    @GetMapping
    public ResponseEntity<List<TenantBusinessResponse>> getAllTenantBusinesses() {
        return ResponseEntity.ok(tenantBusinessService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantBusinessResponse> getTenantBusinessById(@PathVariable Long id) {
        return ResponseEntity.ok(tenantBusinessService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TenantBusinessResponse> createTenantBusiness(@Valid @RequestBody TenantBusinessRequest request) {
        TenantBusinessResponse response = tenantBusinessService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantBusinessResponse> updateTenantBusiness(
            @PathVariable Long id,
            @Valid @RequestBody TenantBusinessRequest request
    ) {
        TenantBusinessResponse response = tenantBusinessService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tenantBusinessService.deleteById(id);
    }
}
