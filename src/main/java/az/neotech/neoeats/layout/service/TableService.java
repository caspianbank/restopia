package az.neotech.neoeats.layout.service;

import az.neotech.neoeats.layout.domain.dto.request.TableRequest;
import az.neotech.neoeats.layout.domain.dto.responce.TableResponse;
import az.neotech.neoeats.layout.domain.enums.TableStatus;

import java.util.List;

public interface TableService {
    TableResponse create(TableRequest request);
    List<TableResponse> getAll();
    TableResponse getById(Long id);
    void delete(Long id);
    TableResponse update(Long id, TableRequest request);
    TableResponse changeStatus(Long id, TableStatus status);
    TableResponse getByCode(String code);
    String generateQrCodeForTable(Long tableId);

}
