package az.restopia.business.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TimeOffResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}