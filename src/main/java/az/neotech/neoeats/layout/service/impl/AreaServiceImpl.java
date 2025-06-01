package az.neotech.neoeats.layout.service.impl;

import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.layout.domain.dto.request.AreaRequest;
import az.neotech.neoeats.layout.domain.dto.responce.AreaResponse;
import az.neotech.neoeats.layout.domain.entity.Area;
import az.neotech.neoeats.layout.domain.mapper.AreaMapper;
import az.neotech.neoeats.layout.repository.AreaRepository;
import az.neotech.neoeats.layout.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: log statements
@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;

    @Override
    public AreaResponse create(AreaRequest request) {
        Area area = areaMapper.toEntity(request);
        return areaMapper.toResponse(areaRepository.save(area));
    }
    @Override
    public List<AreaResponse> getAll() {
        return areaMapper.toResponseList(areaRepository.findAll());
    }

    @Override
    public AreaResponse getById(Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Area not found"));
        return areaMapper.toResponse(area);
    }

    public AreaResponse getByCode(String code) {
        Area area = areaRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RecordNotFoundException("Area with code " + code + " not found"));
        return areaMapper.toResponse(area);
    }

    @Override
    public void delete(Long id) {
        areaRepository.deleteById(id); // todo: soft deletes only
    }

    @Override
    public AreaResponse update(Long id, AreaRequest request) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Area not found"));
        area.setName(request.getName());
        area.setDescription(request.getDescription());
        return areaMapper.toResponse(areaRepository.save(area));
    }
}
