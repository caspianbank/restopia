package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.domain.mapper.TenantBusinessStoreMapper;
import az.restopia.business.domain.request.TenantBusinessStoreRequest;
import az.restopia.business.domain.response.TenantBusinessStoreResponse;
import az.restopia.business.repository.TenantBusinessRepository;
import az.restopia.business.repository.TenantBusinessStoreRepository;
import az.restopia.business.service.TenantBusinessStoreService;
import az.restopia.commons.component.Translator;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantBusinessStoreServiceImpl implements TenantBusinessStoreService {
    private final TenantBusinessStoreRepository storeRepository;
    private final TenantBusinessRepository tenantBusinessRepository;
    private final TenantBusinessStoreMapper storeMapper;
    private final Translator translator;

    @Override
    public TenantBusinessStoreResponse createStore(TenantBusinessStoreRequest request) {
        TenantBusiness business = tenantBusinessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        TenantBusinessStore store = storeMapper.toEntity(request);
        store.setBusiness(business);

        TenantBusinessStore saved = storeRepository.save(store);
        return storeMapper.toResponse(saved, business);
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
        return storeMapper.toResponse(updated, store.getBusiness());
    }

    @Override
    public List<TenantBusinessStoreResponse> getAllStores(String tenantCode, Locale locale) {
        TenantBusiness business = tenantBusinessRepository.findAllByTenantCode(tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        return storeRepository.findAllByBusiness(business).stream()
                .map(store -> storeMapper.toResponse(store, business))
                .map(response -> translateOpeningHours(response, locale))
                .toList();
    }

    @Override
    public TenantBusinessStoreResponse getStoreById(Long id, Locale locale) {
        return storeRepository.findById(id)
                .map(store -> storeMapper.toResponse(store, store.getBusiness()))
                .map(response -> translateOpeningHours(response, locale))
                .orElseThrow(() -> new RecordNotFoundException("Store not found"));
    }

    @Override
    public void deleteStore(Long id) {
        TenantBusinessStore entity = storeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Store not found with ID: " + id));
        entity.setDeleteStatus(DeleteStatus.DELETED);
        storeRepository.save(entity);
    }

    private TenantBusinessStoreResponse translateOpeningHours(TenantBusinessStoreResponse response, Locale locale) {
        var translatedOpeningHours = new HashMap<String, String>();
        String prefix = "day.";
        for (Map.Entry<String, String> entry : response.getOpeningHours().entrySet()) {
            String key = prefix + entry.getKey().toLowerCase();
            String translatedDay = translator.translate(key, locale);
            translatedOpeningHours.put(translatedDay, entry.getValue());
        }
        response.setOpeningHours(translatedOpeningHours);
        return response;
    }

}
