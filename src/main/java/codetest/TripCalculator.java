package codetest;

import codetest.PO.Location;
import codetest.PO.Result;
import codetest.PO.Route;
import codetest.exceptions.InvalidEnterLocationException;
import codetest.exceptions.InvalidExitLocationException;
import codetest.exceptions.InvalidLocationException;
import codetest.exceptions.InvalidLocationIdException;
import codetest.exceptions.InvalidLocationNameException;
import codetest.exceptions.SameLocationException;

/**
 * Main java class for Trip calculator
 */
public class TripCalculator {
    protected double cost = 0.25;
    LocationManager locationManager;
    //Cache manager
    TripCacheManager tripCacheManager;

    public TripCalculator() {

    }

    public void setTripCacheManager(TripCacheManager tripCacheManager) {
        this.tripCacheManager = tripCacheManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    /**
     * Calculate distance between two locations.
     * @param startName start location
     * @param endName end location
     * @return calculated distance and cost
     * @throws InvalidLocationException
     */
    public Result tripCalculate(String startName, String endName) throws InvalidLocationException {
    	//retrieve Location by name
        Location begin = locationManager.getByName(startName);
        Location end = locationManager.getByName(endName);
        if (begin != null && end != null) {
        	//location found, invoke internal method to begin the calculation.
            return tripCalculate(begin, end);
        } else {
            if (begin == null) {
                throw new InvalidLocationNameException(startName);
            } else {
                throw new InvalidLocationNameException(endName);
            }
        }
    }

    /**
     * Calculate distance between two locations.
     * @param startId start location
     * @param endId end location
     * @return calculated distance and cost
     * @throws InvalidLocationException
     */
    public Result tripCalculate(int startId, int endId) throws InvalidLocationException {
    	//retrieve Location by ID
        Location begin = locationManager.getById(startId);
        Location end = locationManager.getById(endId);
        if (begin != null && end != null) {
        	//location found, invoke internal method to begin the calculation.
            return tripCalculate(begin, end);
        } else {
            if (begin == null) {
                throw new InvalidLocationIdException(startId);
            } else {
                throw new InvalidLocationIdException(endId);
            }
        }
    }

    /**
     * Internal method. Calculate distance between two locations.
     * @param start start location
     * @param end end location
     * @return calculated distance and cost
     * @throws InvalidLocationException
     */
    protected Result tripCalculate(Location start, Location end) throws InvalidLocationException {
    	
        if (start.getId() == end.getId()) {
        	//if two locations are the same, throw exception
            throw new SameLocationException(start);
        } else if (!start.isEnter()) {
            //check if it is a valid start location
            throw new InvalidEnterLocationException(start);
        } else if (!end.isExit()) {
        	//check if it is a valid exit location
            throw new InvalidExitLocationException(end);
        } else if (start.getId() > end.getId()) {
            //always use smart to large ID to find the distance.
            //swap the start and end
            Location temp = start;
            start = end;
            end = temp;
        }
        //check if the data in cache
        Double result = tripCacheManager.getCache(start.getId(), end.getId());
        if (result == null) {
            //Cache not found, calculate the distance
            // first, find the farthest cached location for start location
            int farthestId = tripCacheManager.findFarthestCachedLocationId(start.getId());
            if (farthestId != start.getId()) {
                //farthest found. calculation begins at that location
                process(start, locationManager.getById(farthestId), end, tripCacheManager.getCache(start.getId(), farthestId));
            } else {
                //nothing found, calculation from begin.
                process(start, start, end, 0);
            }
            //find the result from cache
            result = tripCacheManager.getCache(start.getId(), end.getId());
        }
        if (result != null) {
        	//data found, return the result
            return new Result().setDistance(result).setCost(Math.ceil(result) * cost);
        }
        //data not found
        return null;

    }

    /**
     * This is the main method to calculate the distance between the start and end location.
     * it uses a recursive algorithm to calculate the distance and store the interim distance
     * to the cache manager.
     *
     * @param start    start location
     * @param current  current location
     * @param end      end location
     * @param distance current calculated distance.
     */
    protected void process(Location start, Location current, Location end, double distance) {
        if (current.getId() == end.getId()) {
        	//exit recursive algorithm, if current location reach the end location
            return;
        }
        //find the larger ID and then go further.
        for (Route route : current.getRoutes()) {
            if (current.getId() < route.getToId()) {
            	//larger ID found, calculate the distance and add to the cache
                Location temp = locationManager.getById(route.getToId());
                double tempDistance = distance + route.getDistance();
                tripCacheManager.addCache(start.getId(), temp.getId(), tempDistance);
                process(start, locationManager.getById(route.getToId()), end, tempDistance);
            }

        }


    }

}
