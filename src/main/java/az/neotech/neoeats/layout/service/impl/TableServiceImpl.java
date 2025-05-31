package az.neotech.neoeats.layout.service.impl;

import az.neotech.neoeats.layout.dto.request.TableRequest;
import az.neotech.neoeats.layout.dto.responce.TableResponse;
import az.neotech.neoeats.layout.entity.Area;
import az.neotech.neoeats.layout.entity.RestaurantTable;
import az.neotech.neoeats.layout.enums.TableStatus;
import az.neotech.neoeats.layout.exception.ResourceNotFoundException;
import az.neotech.neoeats.layout.mapper.TableMapper;
import az.neotech.neoeats.layout.repository.AreaRepository;
import az.neotech.neoeats.layout.repository.RestaurantTableRepository;
import az.neotech.neoeats.layout.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantTableRepository tableRepository;
    private final TableMapper tableMapper;
    private final AreaRepository areaRepository;

    @Override
    public TableResponse create(TableRequest request) {
        RestaurantTable table = tableMapper.toEntity(request);
        Area area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new ResourceNotFoundException("Area not found"));
        table.setArea(area);
        table.setStatus(TableStatus.AVAILABLE);
        return tableMapper.toDto(tableRepository.save(table));
    }

    @Override
    public List<TableResponse> getAll() {
        return tableMapper.toDtoList(tableRepository.findAll());
    }

    @Override
    public TableResponse getById(Long id) {
        return tableRepository.findById(id)
                .map(tableMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found"));
    }

    @Override
    public TableResponse getByCode(String code) {
        RestaurantTable table = tableRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Table with code " + code + " not found"));
        return tableMapper.toDto(table);
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableResponse update(Long id, TableRequest request) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found"));
        table.setCode(request.getCode());
        table.setCapacity(request.getCapacity());

        if (!table.getArea().getId().equals(request.getAreaId())) {
            Area area = areaRepository.findById(request.getAreaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Area not found"));
            table.setArea(area);
        }

        return tableMapper.toDto(tableRepository.save(table));
    }

    @Override
    public TableResponse changeStatus(Long id, TableStatus status) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found"));
        table.setStatus(status);
        return tableMapper.toDto(tableRepository.save(table));
    }

}
