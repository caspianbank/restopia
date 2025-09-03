package az.restopia.layout.service;

import az.restopia.layout.domain.dto.request.AreaRequest;
import az.restopia.layout.domain.dto.responce.AreaResponse;

import java.util.List;

public interface AreaService {
    AreaResponse create(AreaRequest request);

    List<AreaResponse> getAll();

    AreaResponse getById(Long id);

    AreaResponse getByCode(String code);

    void delete(Long id);

    AreaResponse update(Long id, AreaRequest request);
}
