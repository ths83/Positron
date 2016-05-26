package fr.univtln.groupc;

import fr.univtln.groupc.algorithm.CAlgorithm;
import junit.framework.TestCase;

import java.text.DecimalFormat;

/**
 * Created by tperron710 on 26/05/16.
 */
public class CHaversineTest extends TestCase{

    public void testDistanceHaversineTest() throws Exception {
        DecimalFormat lDecimalFormat = new DecimalFormat("#.###");
        double lDistance = new CAlgorithm().haversine(38.898556, -77.037852, 38.897147, -77.043934);
        System.out.println(lDistance);
        assertEquals(lDecimalFormat.format(lDistance), "0,549");
    }


}
