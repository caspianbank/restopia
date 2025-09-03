package az.restopia.business.domain.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Named;

import java.util.HashMap;
import java.util.Map;

public class MapperHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Named("jsonToMap")
    public static Map<String, String> jsonToMap(String json) {
        try {
            return json == null ? new HashMap<>() : mapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    @Named("mapToJson")
    public static String mapToJson(Map<String, String> map) {
        try {
            return map == null ? "{}" : mapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert Map to JSON", e);
        }
    }
}
