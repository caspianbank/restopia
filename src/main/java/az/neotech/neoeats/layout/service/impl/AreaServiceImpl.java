package az.neotech.neoeats.layout.service.impl;

import az.neotech.neoeats.layout.domain.dto.request.AreaRequest;
import az.neotech.neoeats.layout.domain.dto.responce.AreaResponse;
import az.neotech.neoeats.layout.domain.entity.Area;
import az.neotech.neoeats.layout.exception.ResourceNotFoundException;
import az.neotech.neoeats.layout.domain.mapper.AreaMapper;
import az.neotech.neoeats.layout.repository.AreaRepository;
import az.neotech.neoeats.layout.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaService areaService;
    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;

    @Override
    public AreaResponse create(AreaRequest request) {
        Area area = areaMapper.toEntity(request);
        return areaMapper.toDto(areaRepository.save(area));
    }
    @Override
    public List<AreaResponse> getAll() {
        return areaMapper.toDtoList(areaRepository.findAll());
    }

    @Override
    public AreaResponse getById(Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found"));
        return areaMapper.toDto(area);
    }

    public AreaResponse getByCode(String code) {
        Area area = areaRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Area with code " + code + " not found"));
        return areaMapper.toDto(area);
    }

    @Override
    public void delete(Long id) {
        areaRepository.deleteById(id);
    }

    @Override
    public AreaResponse update(Long id, AreaRequest request) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found"));
        area.setName(request.getName());
        area.setDescription(request.getDescription());
        return areaMapper.toDto(areaRepository.save(area));
    }
}
