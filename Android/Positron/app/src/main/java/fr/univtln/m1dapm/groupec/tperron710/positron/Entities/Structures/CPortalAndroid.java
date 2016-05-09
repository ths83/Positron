package fr.univtln.m1dapm.groupec.tperron710.positron.Entities.Structures;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by toms on 5/3/16.
 */
public class CPortalAndroid {

        private Marker marker;
        private LatLng latLng;
        private static int id;

        /**
         * constructeur creant un marker sur la carte
         * le constructeur de CPortalAndroid est appele a chaque appel de celui-ci
         * @param googleMap
         * @param latLng
         * @param bitmapDescriptor
         */
        public CPortalAndroid(GoogleMap googleMap, LatLng latLng, BitmapDescriptor bitmapDescriptor) {
            id++;
            this.latLng = latLng;
            marker = googleMap.addMarker
                    (new MarkerOptions().position(latLng).icon(bitmapDescriptor));
            marker.setDraggable(true);
        }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CPortalAndroid.id = id;
    }
}
