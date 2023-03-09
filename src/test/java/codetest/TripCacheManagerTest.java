package codetest;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripCacheManagerTest {


    @Test
    public void normalTest() {
        TripCacheManager tcm = new TripCacheManager();
        tcm.addCache(1, 2, 10);
        tcm.addCache(1, 3, 11);
        tcm.addCache(1, 4, 12);
        tcm.addCache(2, 4, 12);
        assertEquals(10, tcm.getCache(1, 2));
        assertEquals(11, tcm.getCache(1, 3));
        assertEquals(12, tcm.getCache(1, 4));
        assertEquals(null, tcm.getCache(1, 5));
    }

    @Test
    public void findLastCache() {
        TripCacheManager tcm = new TripCacheManager();
        tcm.addCache(1, 2, 10);
        tcm.addCache(1, 3, 11);
        tcm.addCache(1, 4, 12);
        tcm.addCache(2, 5, 25);
        assertEquals(4, tcm.findFarthestCachedLocationId(1));
    }
}