package az.restopia.business.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TimeOffRequest {
    
    @NotNull
    private Long employeeId;
    
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private LocalDate endDate;
    
    private String reason;
    private String description;
}