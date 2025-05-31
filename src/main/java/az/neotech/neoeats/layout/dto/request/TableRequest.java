package az.neotech.neoeats.layout.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {
    private String code;
    private int capacity;
    private Long areaId;
}
