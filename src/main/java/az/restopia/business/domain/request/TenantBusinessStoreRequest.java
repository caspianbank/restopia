package az.restopia.business.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TenantBusinessStoreRequest {
    private Long businessId; // todo: remove this from here and accept it
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
    // todo: whenever this openingHours modified, recommend person to update it on wolt, bolt food
    //  if customer says yes, then send a request to update those services as well.
    private String configurations;
}
