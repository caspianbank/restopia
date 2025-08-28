package az.neotech.neoeats.layout.service.impl;

import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.layout.domain.dto.request.DiningTableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.DiningTableResponse;
import az.neotech.neoeats.layout.domain.entity.Area;
import az.neotech.neoeats.layout.domain.entity.DiningTable;
import az.neotech.neoeats.layout.domain.enums.TableStatus;
import az.neotech.neoeats.layout.domain.mapper.DiningTableMapper;
import az.neotech.neoeats.layout.repository.AreaRepository;
import az.neotech.neoeats.layout.repository.DiningTableRepository;
import az.neotech.neoeats.layout.service.DiningTableService;
import az.neotech.neoeats.menu.service.QrCodeService;
import az.neotech.neoeats.menu.util.QrCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// todo: log statements
// todo: find by code or id in separate method for remove duplication
@Service
@RequiredArgsConstructor
public class DiningTableServiceImpl implements DiningTableService {

    private final DiningTableRepository tableRepository;
    private final DiningTableMapper diningTableMapper;
    private final AreaRepository areaRepository;
    private final QrCodeService qrCodeService;

    @Value("${qrmenu.base-url}") // from application.yml
    private String baseUrl;

    @Override
    public DiningTableResponse create(DiningTableRequest request) {
        DiningTable table = diningTableMapper.toEntity(request);
        Area area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RecordNotFoundException("Area not found"));
        table.setArea(area);
        table.setStatus(TableStatus.AVAILABLE);

        // QR üçün unikal kod (UUID və ya istəyə görə custom string)
        String qrCode = UUID.randomUUID().toString();
        table.setQrCode(qrCode);

        // Məlumat bazasına qeyd et
        DiningTable saved = tableRepository.save(table);

        // QR kod linki (menu baxışı üçün endpoint)
        String qrCodeUrl = baseUrl + "/api/v1/qrmenu/" + qrCode;

        // QR kod şəkli yarat
        qrCodeService.generateQrImageForTable(saved.getId(), qrCodeUrl);

        return diningTableMapper.toResponse(saved);
    }

    @Override
    public List<DiningTableResponse> getAll() {
        return diningTableMapper.toResponseList(tableRepository.findAll());
    }

    @Override
    public List<DiningTableResponse> getAllByTenantCode(String tenantCode) {
        return tableRepository.findAllByTenantCode(tenantCode).stream()
                .map(diningTableMapper::toResponse)
                .toList();
    }

    @Override
    public DiningTableResponse getById(Long id) {
        return tableRepository.findById(id)
                .map(diningTableMapper::toResponse)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
    }

    @Override
    public DiningTableResponse getByCode(String code) {
        DiningTable table = tableRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RecordNotFoundException("Table with code " + code + " not found"));
        return diningTableMapper.toResponse(table);
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public DiningTableResponse update(Long id, DiningTableRequest request) {
        DiningTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
        table.setCode(request.getCode());
        table.setCapacity(request.getCapacity());

        if (!table.getArea().getId().equals(request.getAreaId())) {
            Area area = areaRepository.findById(request.getAreaId())
                    .orElseThrow(() -> new RecordNotFoundException("Area not found"));
            table.setArea(area);
        }

        return diningTableMapper.toResponse(tableRepository.save(table));
    }

    @Override
    public DiningTableResponse changeStatus(Long id, TableStatus status) {
        DiningTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));
        table.setStatus(status);
        return diningTableMapper.toResponse(tableRepository.save(table));
    }

    @Override
    public String generateQrCodeForTable(Long tableId) {
        DiningTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RecordNotFoundException("Table not found"));

        String qrLink = baseUrl + "/qrmenu/" + table.getQrCode();
        try {
            return QrCodeGenerator.generateQrCodeBase64(qrLink, 300, 300);
        } catch (Exception e) {
            throw new RuntimeException("QR kod was not creat", e);
        }
    }

}
