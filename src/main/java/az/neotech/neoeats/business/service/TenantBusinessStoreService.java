package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessStoreResponse;

import java.util.List;

public interface TenantBusinessStoreService {
    TenantBusinessStoreResponse createStore(TenantBusinessStoreRequest request);

    TenantBusinessStoreResponse updateStore(Long id, TenantBusinessStoreRequest request);

    List<TenantBusinessStoreResponse> getAllStores();

    TenantBusinessStoreResponse getStoreById(Long id);

    void deleteStore(Long id);
}
