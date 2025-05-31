package az.neotech.neoeats.layout.service;

import az.neotech.neoeats.layout.dto.request.TableRequest;
import az.neotech.neoeats.layout.dto.responce.TableResponse;
import az.neotech.neoeats.layout.enums.TableStatus;

import java.util.List;

public interface TableService {
    TableResponse create(TableRequest request);
    List<TableResponse> getAll();
    TableResponse getById(Long id);
    void delete(Long id);
    TableResponse update(Long id, TableRequest request);
    TableResponse changeStatus(Long id, TableStatus status);
    TableResponse getByCode(String code);
}
