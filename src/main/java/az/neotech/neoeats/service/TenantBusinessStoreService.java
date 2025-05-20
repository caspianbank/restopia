package az.neotech.neoeats.service;

import az.neotech.neoeats.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.domain.response.TenantBusinessStoreResponse;

import java.util.List;

public interface TenantBusinessStoreService {
    TenantBusinessStoreResponse createStore(TenantBusinessStoreRequest request);

    TenantBusinessStoreResponse updateStore(Long id, TenantBusinessStoreRequest request);

    List<TenantBusinessStoreResponse> getAllStores();

    TenantBusinessStoreResponse getStoreById(Long id);

    void deleteStore(Long id);
}
