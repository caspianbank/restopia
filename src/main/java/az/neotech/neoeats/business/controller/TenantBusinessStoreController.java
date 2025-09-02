package az.neotech.neoeats.business.controller;

import az.neotech.neoeats.business.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessStoreResponse;
import az.neotech.neoeats.business.service.TenantBusinessStoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant-business-stores")
public class TenantBusinessStoreController {

    private final TenantBusinessStoreService tenantBusinessStoreService;

    @GetMapping
    public List<TenantBusinessStoreResponse> getAllStores(@RequestHeader("X-Tenant-Code") String tenantCode,
                                                          @RequestHeader("Accept-Language") String lang) {
        return tenantBusinessStoreService.getAllStores(tenantCode, Locale.of(lang));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantBusinessStoreResponse createStore(@RequestBody @Valid TenantBusinessStoreRequest request) {
        return tenantBusinessStoreService.createStore(request);
    }

    @GetMapping("/{id}")
    public TenantBusinessStoreResponse getStoreById(@PathVariable Long id,
                                                    @RequestHeader("Accept-Language") String lang) {
        return tenantBusinessStoreService.getStoreById(id, Locale.of(lang));
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
