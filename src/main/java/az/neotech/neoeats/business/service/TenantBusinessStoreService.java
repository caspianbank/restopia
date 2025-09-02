package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessStoreResponse;

import java.util.List;
import java.util.Locale;

public interface TenantBusinessStoreService {
    TenantBusinessStoreResponse createStore(TenantBusinessStoreRequest request);

    TenantBusinessStoreResponse updateStore(Long id, TenantBusinessStoreRequest request);

    List<TenantBusinessStoreResponse> getAllStores(String tenantCode, Locale locale);

    TenantBusinessStoreResponse getStoreById(Long id, Locale locale);

    void deleteStore(Long id);
}
