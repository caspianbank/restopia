package az.neotech.neoeats.layout.service.impl;

import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.layout.domain.dto.request.TableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.TableResponse;
import az.neotech.neoeats.layout.domain.entity.Area;
import az.neotech.neoeats.layout.domain.entity.RestaurantTable;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import az.neotech.neoeats.layout.domain.mapper.TableMapper;
import az.neotech.neoeats.layout.repository.AreaRepository;
import az.neotech.neoeats.layout.repository.RestaurantTableRepository;
import az.neotech.neoeats.layout.service.TableService;
import az.neotech.neoeats.qrmenu.service.QrCodeService;
import az.neotech.neoeats.qrmenu.util.QrCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// todo: log statements
// todo: find by code or id in separate method for remove duplication
@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantTableRepository tableRepository;
    private final TableMapper tableMapper;
    private final AreaRepository areaRepository;
    private final QrCodeService qrCodeService;

    @Value("${qrmenu.base-url}") // from application.yml
    private String baseUrl;

    @Override
    public TableResponse create(TableRequest request) {
        RestaurantTable table = tableMapper.toEntity(request);
        Area area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RecordNotFoundException("Area not found"));
        table.setArea(area);
        table.setStatus(TableStatus.AVAILABLE);

        // QR üçün unikal kod (UUID və ya istəyə görə custom string)
        String qrCode = UUID.randomUUID().toString();
        table.setQrCode(qrCode);

        // Məlumat bazasına qeyd et
        RestaurantTable saved = tableRepository.save(table);

        // QR kod linki (menu baxışı üçün endpoint)
        String qrCodeUrl = baseUrl + "/api/v1/qrmenu/" + qrCode;

        // QR kod şəkli yarat
        qrCodeService.generateQrImageForTable(saved.getId(), qrCodeUrl);


        return tableMapper.toResponse(saved);
    }

    @Override
    public List<TableResponse> getAll() {
        return tableMapper.toResponseList(tableRepository.findAll());
    }

    @Override
    public TableResponse getById(Long id) {
        return tableRepository.findById(id)
                .map(tableMapper::toResponse)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
    }

    @Override
    public TableResponse getByCode(String code) {
        RestaurantTable table = tableRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RecordNotFoundException("Table with code " + code + " not found"));
        return tableMapper.toResponse(table);
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableResponse update(Long id, TableRequest request) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
        table.setCode(request.getCode());
        table.setCapacity(request.getCapacity());

        if (!table.getArea().getId().equals(request.getAreaId())) {
            Area area = areaRepository.findById(request.getAreaId())
                    .orElseThrow(() -> new RecordNotFoundException("Area not found"));
            table.setArea(area);
        }

        return tableMapper.toResponse(tableRepository.save(table));
    }

    @Override
    public TableResponse changeStatus(Long id, TableStatus status) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
        table.setStatus(status);
        return tableMapper.toResponse(tableRepository.save(table));
    }

    @Override
    public String generateQrCodeForTable(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));

        String qrLink = baseUrl + "/qrmenu/" + table.getQrCode();
        try {
            return QrCodeGenerator.generateQrCodeBase64(qrLink, 300, 300);
        } catch (Exception e) {
            throw new RuntimeException("QR kod was not creat", e);
        }
    }

}
