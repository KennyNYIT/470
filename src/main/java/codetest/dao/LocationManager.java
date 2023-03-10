package codetest.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import codetest.PO.Location;
import codetest.util.JSONLocationBuilder;
import jakarta.annotation.PostConstruct;

/**
 * This class help to manage locations data. Location can be looked up by ID or
 * name.
 */
@Component
public class LocationManager implements LocationDAO {

	Map<Integer, Location> locationById;
	Map<String, Location> locationByName;

	public LocationManager() {

	}

	@Value("classpath:interchanges.json")
	Resource resourceFile;

	@PostConstruct
	public void postConstruct() {
		System.out.println("load configuration file");
		try {
			String json;
			json = new String(resourceFile.getContentAsByteArray(), StandardCharsets.UTF_8);
			this.setLocationById(new JSONLocationBuilder(json).getLocationList());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public LocationManager setLocationById(Map<Integer, Location> locationById) {
		this.locationById = locationById;
		buildByNameMap();
		return this;
	}

	/**
	 * Construct the locations' data in other views; location data can be looked up
	 * by name
	 */
	private void buildByNameMap() {
		if (locationById != null) {
			// location data found, build another view.
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
	 * 
	 * @param id
	 * @return location, or null if data not exist
	 */
	public Location getById(int id) {
		return locationById.get(id);
	}

	/**
	 * get location by name
	 * 
	 * @param name
	 * @return location, or null if data not exist
	 */
	public Location getByName(String name) {
		return locationByName.get(name.toLowerCase());
	}

}
