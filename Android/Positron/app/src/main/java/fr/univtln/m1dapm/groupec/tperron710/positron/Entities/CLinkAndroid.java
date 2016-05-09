package fr.univtln.m1dapm.groupec.tperron710.positron.Entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by toms on 08/05/2016.
 */
public class CLinkAndroid {

    private LatLng firstPortal;
    private LatLng secondPortal;

    public CLinkAndroid(LatLng firstPortal, LatLng secondPortal) {
        this.firstPortal = firstPortal;
        this.secondPortal = secondPortal;
    }
}
