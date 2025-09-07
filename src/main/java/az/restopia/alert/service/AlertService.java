package az.restopia.alert.service;

import az.restopia.alert.domain.request.AlertCreateRequest;
import az.restopia.alert.domain.request.AlertUpdateRequest;
import az.restopia.alert.domain.response.AlertResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {

    Page<AlertResponse> getAllAlerts(String tenantCode, Pageable pageable);

    AlertResponse createAlert(AlertCreateRequest request);

    AlertResponse updateAlert(Long id, String tenantCode, AlertUpdateRequest request);

    void deleteAlert(Long id, String tenantCode);

    Page<AlertResponse> searchAlerts(String q, String tenantCode, Pageable pageable);
}