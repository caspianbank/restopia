package az.neotech.neoeats.controller;

import az.neotech.neoeats.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.domain.response.TenantBusinessStoreResponse;
import az.neotech.neoeats.service.TenantBusinessStoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants/{code}/businesses/{id}/stores")
@RequiredArgsConstructor
public class TenantBusinessStoreController {

    private final TenantBusinessStoreService tenantBusinessStoreService;

    @GetMapping
    public List<TenantBusinessStoreResponse> getAllStores() {
        return tenantBusinessStoreService.getAllStores();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantBusinessStoreResponse createStore(@RequestBody @Valid TenantBusinessStoreRequest request) {
        return tenantBusinessStoreService.createStore(request);
    }

    @GetMapping("/{id}")
    public TenantBusinessStoreResponse getStoreById(@PathVariable Long id) {
        return tenantBusinessStoreService.getStoreById(id);
    }

    @PutMapping("/{id}")
    public TenantBusinessStoreResponse updateStore(
            @PathVariable Long id,
            @RequestBody @Valid TenantBusinessStoreRequest request
    ) {
        return tenantBusinessStoreService.updateStore(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable Long id) {
        tenantBusinessStoreService.deleteStore(id);
    }
}
