package az.restopia.business.domain.response;

import az.restopia.business.domain.enums.StoreType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class TenantBusinessStoreResponse {
    private Long storeId;
    private Long businessId;
    private String name;
    private String description;
    private String email;
    private String phone;
    private String location;
    private String city;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String contactNumber;
    private StoreType storeType;
    private boolean isActive;
    private Map<String, String> socials; // todo: consider more detailed social media channels with images
    private Map<String, String> openingHours; // todo: consider AM and PM styles in future
    private String configurations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
