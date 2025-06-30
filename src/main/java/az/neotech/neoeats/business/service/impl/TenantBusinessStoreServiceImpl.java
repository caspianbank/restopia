package az.neotech.neoeats.business.service.impl;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import az.neotech.neoeats.business.domain.mapper.TenantBusinessStoreMapper;
import az.neotech.neoeats.business.domain.request.TenantBusinessStoreRequest;
import az.neotech.neoeats.business.domain.response.TenantBusinessStoreResponse;
import az.neotech.neoeats.business.repository.TenantBusinessRepository;
import az.neotech.neoeats.business.repository.TenantBusinessStoreRepository;
import az.neotech.neoeats.business.service.TenantBusinessStoreService;
import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantBusinessStoreServiceImpl implements TenantBusinessStoreService {
    private final TenantBusinessStoreRepository storeRepository;
    private final TenantBusinessRepository tenantBusinessRepository;
    private final TenantBusinessStoreMapper storeMapper;

    @Override
    public TenantBusinessStoreResponse createStore(TenantBusinessStoreRequest request) {
        TenantBusiness business = tenantBusinessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        TenantBusinessStore store = storeMapper.toEntity(request);
        store.setBusiness(business);

        TenantBusinessStore saved = storeRepository.save(store);
        return storeMapper.toResponse(saved);
    }

    @Override
    public TenantBusinessStoreResponse updateStore(Long id, TenantBusinessStoreRequest request) {
        TenantBusinessStore store = storeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Store not found"));

        storeMapper.updateEntityFromRequest(request, store);

        if (request.getBusinessId() != null) {
            TenantBusiness business = tenantBusinessRepository.findById(request.getBusinessId())
                    .orElseThrow(() -> new RecordNotFoundException("Business not found"));
            store.setBusiness(business);
        }

        TenantBusinessStore updated = storeRepository.save(store);
        return storeMapper.toResponse(updated);
    }

    @Override
    public List<TenantBusinessStoreResponse> getAllStores() {
        return storeRepository.findAll().stream()
                .map(storeMapper::toResponse)
                .toList();
    }

    @Override
    public TenantBusinessStoreResponse getStoreById(Long id) {
        return storeRepository.findById(id)
                .map(storeMapper::toResponse)
                .orElseThrow(() -> new RecordNotFoundException("Store not found"));
    }

    @Override
    public void deleteStore(Long id) {
        TenantBusinessStore entity = storeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Store not found with ID: " + id));
        entity.setDeleteStatus(DeleteStatus.DELETED);
        storeRepository.save(entity);
    }
}
