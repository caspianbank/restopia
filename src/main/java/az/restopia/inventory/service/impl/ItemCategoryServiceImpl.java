package az.restopia.inventory.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.ItemCategory;
import az.restopia.inventory.domain.mapper.ItemCategoryMapper;
import az.restopia.inventory.domain.request.ItemCategoryRequest;
import az.restopia.inventory.domain.response.ItemCategoryResponse;
import az.restopia.inventory.repository.ItemCategoryRepository;
import az.restopia.inventory.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// todo: get by id in separate method
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository repository;
    private final ItemCategoryMapper mapper;

    @Override
    public List<ItemCategoryResponse> getAllItemCategories() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ItemCategoryResponse getItemCategoryById(Long id) {
        ItemCategory category = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Item category not found with id: " + id));
        return mapper.toResponse(category);
    }

    @Override
    public ItemCategoryResponse createItemCategory(ItemCategoryRequest request) {
        ItemCategory category = mapper.toEntity(request);
        if (request.getParentId() != null) {
            Optional<ItemCategory> parentOpt = repository.findById(request.getParentId());
            parentOpt.ifPresent(category::setParent);
        }
        ItemCategory saved = repository.save(category);
        log.info("Item category created with id: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    public ItemCategoryResponse updateItemCategory(Long id, ItemCategoryRequest request) {
        ItemCategory existing = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Item category not found with id: " + id));

        mapper.updateItemCategoryFromRequest(request, existing);

        if (request.getParentId() != null) {
            Optional<ItemCategory> parentOpt = repository.findById(request.getParentId());
            parentOpt.ifPresent(existing::setParent);
        } else {
            existing.setParent(null);
        }

        ItemCategory updated = repository.save(existing);
        log.info("Item category updated with id: {}", updated.getId());
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteItemCategory(Long id) {
        ItemCategory existing = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Item category not found with id: " + id));
        existing.setDeleteStatus(DeleteStatus.DELETED);
        repository.save(existing);
        log.info("Item category deleted with id: {}", id);
    }
}
