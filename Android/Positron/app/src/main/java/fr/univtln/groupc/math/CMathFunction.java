package fr.univtln.groupc.math;

/**
 * Created by tperron710 on 19/05/16.
 */
public class CMathFunction {

    // TODO unit tests in java server
    public static final double EARTH_RADIUS = 6372.8; // In kilometers
    public static final double MILLE = 1000;


    public static double haversine(double lLat1, double lLon1, double lLat2, double lLon2) {

        double lLat = Math.toRadians(lLat2 - lLat1);
        double lLon = Math.toRadians(lLon2 - lLon1);

        lLat1 = Math.toRadians(lLat1);
        lLat2 = Math.toRadians(lLat2);

        double lA = Math.pow(Math.sin(lLat / 2),2) + Math.pow(Math.sin(lLon / 2),2) * Math.cos(lLat1) * Math.cos(lLat2);
        double lC = 2 * Math.asin(Math.sqrt(lA));

        return EARTH_RADIUS * lC * MILLE ;
    }


}
