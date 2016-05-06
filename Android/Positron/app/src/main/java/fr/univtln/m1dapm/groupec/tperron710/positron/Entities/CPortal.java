package fr.univtln.m1dapm.groupec.tperron710.positron.Entities;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by toms on 5/3/16.
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
