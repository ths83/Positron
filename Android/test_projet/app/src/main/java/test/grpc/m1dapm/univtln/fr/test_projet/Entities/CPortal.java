package test.grpc.m1dapm.univtln.fr.test_projet.Entities;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by toms on 06/05/16.
 */
public class CPortal {
    private Marker marker;

    /**
     * constructeur creant un marker sur la carte
     * le constructeur de CPortal est appele a chaque appel de celui-ci
     * @param googleMap
     * @param latLng
     * @param bitmapDescriptor
     */
    public CPortal(GoogleMap googleMap, LatLng latLng, BitmapDescriptor bitmapDescriptor) {
        marker = googleMap.addMarker
                (new MarkerOptions().position(latLng).icon(bitmapDescriptor));
        marker.setDraggable(true);
    }

}
