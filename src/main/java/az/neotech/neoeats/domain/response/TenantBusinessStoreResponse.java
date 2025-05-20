package az.neotech.neoeats.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TenantBusinessStoreResponse {
    private Long storeId;
    private Long businessId;
    private String name;
    private String location;
    private String city;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String contactNumber;
    private String storeType;
    private Boolean isActive;
    private String openingHours;
    private String configurations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
