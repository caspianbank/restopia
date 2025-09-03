package az.restopia.business.controller;

import az.restopia.business.domain.request.TenantRequest;
import az.restopia.business.domain.response.TenantResponse;
import az.restopia.business.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public List<TenantResponse> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public TenantResponse createTenant(@RequestBody TenantRequest tenantRequest) {
        return tenantService.createTenant(tenantRequest);
    }

    @PutMapping("/{code}")
    public TenantResponse updateTenant(@PathVariable String code, @RequestBody TenantRequest tenantRequest) {
        return tenantService.updateTenant(code, tenantRequest);
    }

    @DeleteMapping("/{code}")
    public void deleteTenant(@PathVariable String code) {
        tenantService.deleteTenant(code);
    }

    @GetMapping("/{code}")
    public TenantResponse getTenantByCode(@PathVariable String code) {
        return tenantService.getTenantByCode(code);
    }

}
