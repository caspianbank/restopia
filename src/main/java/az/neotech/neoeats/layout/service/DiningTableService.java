package az.neotech.neoeats.layout.service;

import az.neotech.neoeats.layout.domain.dto.request.DiningTableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.DiningTableResponse;
import az.neotech.neoeats.layout.domain.enums.TableStatus;

import java.util.List;

public interface DiningTableService {
    DiningTableResponse create(DiningTableRequest request);

    List<DiningTableResponse> getAll();

    List<DiningTableResponse> getAllByTenantCode(String tenantCode);

    DiningTableResponse getById(Long id);

    void delete(Long id);

    DiningTableResponse update(Long id, DiningTableRequest request);

    DiningTableResponse changeStatus(Long id, TableStatus status);

    DiningTableResponse getByCode(String code);

    String generateQrCodeForTable(Long tableId);
}
