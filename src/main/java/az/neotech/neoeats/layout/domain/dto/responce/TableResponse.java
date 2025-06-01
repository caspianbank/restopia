package az.neotech.neoeats.layout.domain.dto.responce;

import az.neotech.neoeats.layout.domain.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableResponse {
    private String code;
    private int capacity;
    private TableStatus status;
    private Long areaId;
}
