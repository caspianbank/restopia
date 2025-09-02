package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import az.neotech.neoeats.business.domain.request.TenantBusinessRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessResponse;

import java.util.List;

public interface TenantBusinessService {

    TenantBusinessResponse create(TenantBusinessRequest request);

    TenantBusinessResponse update(Long id, TenantBusinessRequest request);

    List<TenantBusinessResponse> getAll();

    TenantBusinessResponse getById(Long id);

    TenantBusiness getByIdOrThrow(Long id);

    void deleteById(Long id);
}
