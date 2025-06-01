package az.neotech.neoeats.layout.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {
    private String code;
    private int capacity;
    private Long areaId;
}
