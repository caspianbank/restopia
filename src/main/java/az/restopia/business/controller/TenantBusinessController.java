package az.restopia.business.controller;

import az.restopia.business.domain.request.TenantBusinessRequest;
import az.restopia.business.domain.response.TenantBusinessResponse;
import az.restopia.business.service.TenantBusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenants/{code}/businesses")
public class TenantBusinessController {

    private final TenantBusinessService tenantBusinessService;

    @GetMapping
    public List<TenantBusinessResponse> getAllTenantBusinesses() {
        return tenantBusinessService.getAll();
    }

    @GetMapping("/{id}")
    public TenantBusinessResponse getTenantBusinessById(@PathVariable Long id) {
        return tenantBusinessService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantBusinessResponse createTenantBusiness(@Valid @RequestBody TenantBusinessRequest request) {
        return tenantBusinessService.create(request);
    }

    @PutMapping("/{id}")
    public TenantBusinessResponse updateTenantBusiness(
            @PathVariable Long id,
            @Valid @RequestBody TenantBusinessRequest request
    ) {
        return tenantBusinessService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tenantBusinessService.deleteById(id);
    }
}
