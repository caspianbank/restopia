package az.neotech.neoeats.service;

import java.util.List;

import az.neotech.neoeats.domain.request.TenantBusinessRequest;
import az.neotech.neoeats.domain.response.TenantBusinessResponse;

public interface TenantBusinessService {

    TenantBusinessResponse create(TenantBusinessRequest request);

    TenantBusinessResponse update(Long id, TenantBusinessRequest request);

    List<TenantBusinessResponse> getAll();

    TenantBusinessResponse getById(Long id);

    void deleteById(Long id);
}
