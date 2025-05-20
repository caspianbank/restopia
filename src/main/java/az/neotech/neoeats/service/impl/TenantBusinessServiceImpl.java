package az.neotech.neoeats.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import az.neotech.neoeats.domain.entity.TenantBusiness;
import az.neotech.neoeats.domain.enums.DeleteStatus;
import az.neotech.neoeats.domain.mapper.TenantBusinessMapper;
import az.neotech.neoeats.domain.request.TenantBusinessRequest;
import az.neotech.neoeats.domain.response.TenantBusinessResponse;
import az.neotech.neoeats.exception.RecordNotFoundException;
import az.neotech.neoeats.repository.TenantBusinessRepository;
import az.neotech.neoeats.service.TenantBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantBusinessServiceImpl implements TenantBusinessService {

    private final TenantBusinessRepository tenantBusinessRepository;
    private final TenantBusinessMapper tenantBusinessMapper;

    @Override
    public TenantBusinessResponse create(TenantBusinessRequest request) {
        TenantBusiness entity = tenantBusinessMapper.toEntity(request);
        entity.setDeleteStatus(DeleteStatus.ACTIVE);
        TenantBusiness saved = tenantBusinessRepository.save(entity);
        return tenantBusinessMapper.toResponse(saved);
    }

    @Override
    public TenantBusinessResponse update(Long id, TenantBusinessRequest request) {
        TenantBusiness existing = tenantBusinessRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("TenantBusiness not found with id: " + id));

        tenantBusinessMapper.updateEntityFromRequest(request, existing);

        TenantBusiness updated = tenantBusinessRepository.save(existing);
        return tenantBusinessMapper.toResponse(updated);
    }

    @Override
    public List<TenantBusinessResponse> getAll() {
        return tenantBusinessRepository.findAll().stream()
                .map(tenantBusinessMapper::toResponse)
                .toList();
    }

    @Override
    public TenantBusinessResponse getById(Long id) {
        TenantBusiness entity = tenantBusinessRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("TenantBusiness not found with id: " + id));
        return tenantBusinessMapper.toResponse(entity);
    }

    @Override
    public void deleteById(Long id) {
        TenantBusiness entity;
        entity = tenantBusinessRepository.findById(id)
                .orElseThrow(() -> {
                    return new RecordNotFoundException("TenantBusiness not found with id: " + id);
                });
        entity.setDeleteStatus(DeleteStatus.DELETED);
        tenantBusinessRepository.save(entity);
    }
}
