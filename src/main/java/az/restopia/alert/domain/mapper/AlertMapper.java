package az.restopia.alert.domain.mapper;

import az.restopia.alert.domain.entity.Alert;
import az.restopia.alert.domain.request.AlertCreateRequest;
import az.restopia.alert.domain.response.AlertResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlertMapper {

    AlertResponse toResponse(Alert alert);

    List<AlertResponse> toResponseList(List<Alert> alerts);

    Alert toEntity(AlertCreateRequest request);
}