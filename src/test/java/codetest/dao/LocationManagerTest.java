package codetest.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import codetest.PO.Location;
import codetest.PO.Route;

class LocationManagerTest {

    @Test
    public void normalTest() {
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(1)});
        Location l2 = new Location().setId(2).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(1)});

        Map<Integer, Location> byId = new HashMap<>();
        byId.put(1, l1);
        byId.put(2, l2);

        LocationManager lr = new LocationManager().setLocationById(byId);
        assertEquals(l1, lr.getByName("1st"));
        assertEquals(l1, lr.getById(1));
        assertEquals(l2, lr.getByName("2nd"));
        assertEquals(l2, lr.getById(2));
        assertEquals(null, lr.getByName("3rd"));


    }

    @Test
    public void noExitAndEnterTest(){
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(1).setExit(false)});
        Location l2 = new Location().setId(2).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(1).setEnter(false)});
        Map<Integer, Location> byId = new HashMap<>();
        byId.put(1, l1);
        byId.put(2, l2);

        LocationManager lr = new LocationManager().setLocationById(byId);
        assertEquals(false, lr.getById(2).isExit());
        assertEquals(true, lr.getById(1).isExit());

        assertEquals(false, lr.getById(1).isEnter());
        assertEquals(true, lr.getById(2).isEnter());

    }
}