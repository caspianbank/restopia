package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.menu.domain.entity.MenuCategory;
import az.neotech.neoeats.menu.domain.mapper.MenuCategoryMapper;
import az.neotech.neoeats.menu.domain.request.MenuCategoryRequest;
import az.neotech.neoeats.menu.domain.response.MenuCategoryResponse;
import az.neotech.neoeats.menu.repository.MenuCategoryRepository;
import az.neotech.neoeats.menu.service.MenuCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuCategoryMapper menuCategoryMapper;

    @Override
    public List<MenuCategoryResponse> getAllMenuCategories() {
        log.info("Fetching all menu categories");
        return menuCategoryRepository.findAll().stream()
                .map(menuCategoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuCategoryResponse getMenuCategoryById(Long id) {
        log.info("Fetching menu category with id: {}", id);
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("MenuCategory not found with id: " + id));
        return menuCategoryMapper.toResponse(menuCategory);
    }

    @Override
    public MenuCategoryResponse createMenuCategory(MenuCategoryRequest request) {
        log.info("Creating menu category");
        MenuCategory menuCategory = menuCategoryMapper.toEntity(request);
        return menuCategoryMapper.toResponse(menuCategoryRepository.save(menuCategory));
    }

    @Override
    public MenuCategoryResponse updateMenuCategory(Long id, MenuCategoryRequest request) {
        log.info("Updating menu category with id: {}", id);
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("MenuCategory not found with id: " + id));

        menuCategory.setName(request.getName());
        menuCategory.setDescription(request.getDescription());
        menuCategory.setPosition(request.getPosition());
        menuCategory.setActive(request.isActive());
        return menuCategoryMapper.toResponse(menuCategoryRepository.save(menuCategory));
    }

    @Override
    public void deleteMenuCategory(Long id) {
        log.info("Deleting menu category with id: {}", id);
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("MenuCategory not found with id: " + id));
        menuCategory.setDeleteStatus(DeleteStatus.DELETED);
        menuCategoryRepository.save(menuCategory);
    }
}