package az.restopia.business.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCountResponse {
    private long totalEmployees;
    private long activeEmployees;
    private long inactiveEmployees;
}