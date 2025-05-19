package az.neotech.neoeats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.neotech.neoeats.domain.request.TenantRequest;
import az.neotech.neoeats.domain.response.TenantResponse;
import az.neotech.neoeats.service.TenantService;

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
