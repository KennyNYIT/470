package codetest;

import codetest.PO.Location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class help to manage locations data.
 * Location can be looked up by ID or name.
 */
public class LocationManager {

    Map<Integer, Location> locationById;
    Map<String, Location> locationByName;

    public LocationManager(Map<Integer, Location> locationById) {
        this.locationById = locationById;
        buildByNameMap();
    }

    /**
     * Construct the locations' data in other views; location data can be looked up by name
     */
    private void buildByNameMap() {
        if (locationById != null) {
            //location data found, build another view.
            locationByName = new HashMap<>();
            locationById.keySet().forEach(id -> {
                Location temp = locationById.get(id);
                temp.setId(id);
                Arrays.stream(temp.getRoutes()).forEach(r -> {
                    if (!r.isEnter()) {
                        locationById.get(r.getToId()).setEnter(false);
                    }
                    if (!r.isExit()) {
                        locationById.get(r.getToId()).setExit(false);
                    }
                });
                locationByName.put(temp.getName().toLowerCase(), temp);
            });
        }
    }

    /**
     * get location by ID
     * @param id
     * @return location, or null if data not exist
     */
    public Location getById(int id) {
        return locationById.get(id);
    }

    /**
     * get location by name
     * @param name
     * @return location, or null if data not exist
     */
    public Location getByName(String name) {
        return locationByName.get(name.toLowerCase());
    }

}
