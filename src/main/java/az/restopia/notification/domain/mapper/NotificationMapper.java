package az.restopia.notification.domain.mapper;

import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.request.NotificationCreateRequest;
import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    NotificationResponse toResponse(Notification notification);

    List<NotificationResponse> toResponseList(List<Notification> notifications);

    NotificationSummaryResponse toSummaryResponse(Notification notification);

    List<NotificationSummaryResponse> toSummaryResponseList(List<Notification> notifications);

    Notification toEntity(NotificationCreateRequest request);
}