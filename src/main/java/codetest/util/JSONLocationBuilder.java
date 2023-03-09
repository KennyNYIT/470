package codetest.util;

import codetest.PO.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JSONLocationBuilder implements LocationBuilder {

    private String jsonString;

    private Map<Integer, Location> locationMap = new HashMap<>();

    public JSONLocationBuilder(String jsonString) {
        this.jsonString = jsonString;

    }

    @Override
    public Map<Integer, Location> getLocationList() {
        if (StringUtils.isNotEmpty(jsonString) && locationMap.isEmpty()) {
            TypeReference<HashMap<String,HashMap<Integer, Location>>> typeRef
                    = new TypeReference<HashMap<String,HashMap<Integer, Location>>>() {
            };
            ObjectMapper mapper = new ObjectMapper();
            try {
                locationMap = mapper.readValue(jsonString, typeRef).get("locations");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return locationMap;

    }


}
