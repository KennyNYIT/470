package codetest;


import codetest.PO.Location;
import codetest.PO.Result;
import codetest.PO.Route;
import codetest.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripCalculatorTest {


    @Mock
    TripCacheManager tcm;

    @Mock
    LocationManager lm;

    @Test
    public void normalTest() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        Location l2 = new Location().setId(2).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(6.062),new Route().setToId(3).setDistance(3.847)});
        Location l3 = new Location().setId(3).setLat(3).setLng(3).setName("3rd").setRoutes(new Route[]{new Route().setToId(2).setDistance(3.847)});
        when(lm.getById(1)).thenReturn(l1);
        when(lm.getById(2)).thenReturn(l2);
        when(lm.getById(3)).thenReturn(l3);
        when(tcm.getCache(1,3)).thenReturn(null).thenReturn(2d);
        when(tcm.findFarthestCachedLocationId(1)).thenReturn(1);
        Result r = tc.tripCalculate(1,3);
        assertEquals(2, r.getDistance());
        assertEquals(0.25*2, r.getCost());
    }

    @Test
    public void cacheTest() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(2)});
        Location l2 = new Location().setId(2).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(2),new Route().setToId(3).setDistance(4)});
        Location l3 = new Location().setId(3).setLat(3).setLng(3).setName("3rd").setRoutes(new Route[]{new Route().setToId(2).setDistance(4)});
        when(lm.getById(1)).thenReturn(l1);
        when(lm.getById(2)).thenReturn(l2);
        when(lm.getById(3)).thenReturn(l3);
        when(tcm.getCache(1,3)).thenReturn(null).thenReturn(6d);
        when(tcm.findFarthestCachedLocationId(1)).thenReturn(1);
        Result r = tc.tripCalculate(3,1);
        verify(tcm).addCache(1,3,4+2);
        assertEquals(6, r.getDistance());
        assertEquals(0.25*6, r.getCost());
    }

    @Test
    public void SwapTest() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        Location l2 = new Location().setId(2).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(6.062),new Route().setToId(3).setDistance(3.847)});
        Location l3 = new Location().setId(3).setLat(3).setLng(3).setName("3rd").setRoutes(new Route[]{new Route().setToId(2).setDistance(3.847)});
        when(lm.getById(1)).thenReturn(l1);
        when(lm.getById(2)).thenReturn(l2);
        when(lm.getById(3)).thenReturn(l3);
        when(tcm.getCache(1,3)).thenReturn(null).thenReturn(2d);
        when(tcm.getCache(1,2)).thenReturn(2d);

        when(tcm.findFarthestCachedLocationId(1)).thenReturn(2);
        Result r = tc.tripCalculate(3,1);
        assertEquals(2, r.getDistance());
        assertEquals(0.25*2, r.getCost());
    }

    @Test
    public void InvalidID() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        when(lm.getById(1)).thenReturn(null);
        InvalidLocationIdException exception = assertThrows(InvalidLocationIdException.class, () -> tc.tripCalculate(1,2));
        assertEquals(1, exception.getId());
    }

    @Test
    public void InvalidID2() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        when(lm.getById(1)).thenReturn(l1);
        when(lm.getById(2)).thenReturn(null);
        InvalidLocationIdException exception = assertThrows(InvalidLocationIdException.class, () -> tc.tripCalculate(1,2));
        assertEquals(2, exception.getId());
    }

    @Test
    public void sameLocationException() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        when(lm.getById(1)).thenReturn(l1);

        SameLocationException exception = assertThrows(SameLocationException.class, () -> tc.tripCalculate(1,1));
        assertEquals(l1, exception.getLocation());
    }

    @Test
    public void InvalidName1() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        when(lm.getByName("1")).thenReturn(null);
        when(lm.getByName("2")).thenReturn(null);
        InvalidLocationNameException exception = assertThrows(InvalidLocationNameException.class, () -> tc.tripCalculate("1","2"));
        assertEquals("1", exception.getName());
    }

    @Test
    public void InvalidName2() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        when(lm.getByName("1")).thenReturn(l1);
        when(lm.getByName("2")).thenReturn(null);
        InvalidLocationNameException exception = assertThrows(InvalidLocationNameException.class, () -> tc.tripCalculate("1","2"));
        assertEquals("2", exception.getName());
    }

    @Test
    public void InvalidEnter() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setEnter(false).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        Location l2 = new Location().setId(2).setEnter(false).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(6.062)});
        when(lm.getByName("1")).thenReturn(l1);
        when(lm.getByName("2")).thenReturn(l2);
        InvalidEnterLocationException exception = assertThrows(InvalidEnterLocationException.class, () -> tc.tripCalculate("1","2"));
        assertEquals(l1, exception.getLocation());
    }

    @Test
    public void InvalidExit() throws InvalidLocationException {
        TripCalculator tc = new TripCalculator();
        tc.setTripCacheManager(tcm);
        tc.setLocationManager(lm);
        Location l1 = new Location().setId(1).setLat(1).setLng(1).setName("1st").setRoutes(new Route[]{new Route().setToId(2).setDistance(6.062)});
        Location l2 = new Location().setId(2).setExit(false).setLat(2).setLng(2).setName("2nd").setRoutes(new Route[]{new Route().setToId(1).setDistance(6.062)});
        when(lm.getByName("1")).thenReturn(l1);
        when(lm.getByName("2")).thenReturn(l2);
        InvalidExitLocationException exception = assertThrows(InvalidExitLocationException.class, () -> tc.tripCalculate("1","2"));
        assertEquals(l2, exception.getLocation());
    }
}