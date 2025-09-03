package az.restopia.layout.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiningTableRequest {
    private String code;
    private int capacity;
    private Long areaId;
}
