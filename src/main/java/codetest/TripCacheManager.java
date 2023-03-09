package codetest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cache management class, it will store the distance between two locations
 */
public class TripCacheManager {
    static String keyFormat = "B%04dE%04d";
    static String keyFilter = "B%04dE";

    static String extractPattern = "^B\\d{4}E(\\d{4})$";

    // distance data cached in hashmap
    Map<String, Double> cache = new HashMap<>();

    /**
     * Generate the key for the cache of the distance between the start and end location in a Hashmap.
     * @param startId -
     * @param endId
     * @return generated Key
     */
    protected String generateCacheKey(int startId, int endId) {
        return String.format(keyFormat, startId, endId);
    }

    /**
     * find the last cached location
     * @param beginId
     * @return The id of the last cached location
     */
    protected int findFarthestCachedLocationId(int beginId) {
        try {
            String farthest = cache.keySet().stream().filter(key -> key.startsWith(String.format(keyFilter, beginId))).sorted(Comparator.reverseOrder()).findFirst().get();

            if (farthest != null) {
                Pattern p = Pattern.compile(extractPattern);
                Matcher m = p.matcher(farthest);
                m.matches();
                return Integer.parseInt(m.group(1));

            }
        } catch (Exception e) {
            //Exception occurred, assumed no cache.
        }
        //no any cache, return begin location
        return beginId;
    }

    /**
     * Retrieve the cached data, if not found
     * @param startId
     * @param endId
     * @return cached distance between start and end location
     */
    public Double getCache(int startId, int endId) {
        return cache.get(generateCacheKey(startId, endId));

    }

    /**
     * add the distance to cache
     * @param startId id of start location
     * @param endId id of end location
     * @param distance distance between the start and end location
     */
    public void addCache(int startId, int endId, double distance) {
        cache.put(generateCacheKey(startId, endId), distance);
    }
}
