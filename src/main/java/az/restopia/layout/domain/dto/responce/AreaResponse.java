package az.restopia.layout.domain.dto.responce;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AreaResponse {
    private String name;
    private String description;
    private List<DiningTableResponse> tables;
}
