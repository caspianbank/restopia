package az.restopia.business.service;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.request.TenantBusinessRequest;
import az.restopia.business.domain.response.TenantBusinessResponse;

import java.util.List;

public interface TenantBusinessService {

    TenantBusinessResponse create(TenantBusinessRequest request);

    TenantBusinessResponse update(Long id, TenantBusinessRequest request);

    List<TenantBusinessResponse> getAll();

    TenantBusinessResponse getById(Long id);

    TenantBusiness getByIdOrThrow(Long id);

    void deleteById(Long id);
}
