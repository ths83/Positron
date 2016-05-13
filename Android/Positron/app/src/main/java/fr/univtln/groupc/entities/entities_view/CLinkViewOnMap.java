package fr.univtln.groupc.entities.entities_view;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;

/**
 * Created by toms on 5/13/16.
 */
public class CLinkViewOnMap {

    public static final int NB_PORTALS = 2;
    public static final int LINE_WIDTH = 5;

    private Polyline mLinkLine;
    private CPortalEntity[] mPortalLinkedArray;
    private LatLng[] mLatLngPortalLinkedArray;

    public void onDisplayLink(GoogleMap pMap, CLinkEntity pLink){

        mPortalLinkedArray = (CPortalEntity[]) pLink.getPortals().toArray();
        mLatLngPortalLinkedArray = new LatLng[NB_PORTALS];
        for (int i = 0; i < NB_PORTALS; i++){
            mLatLngPortalLinkedArray[i] = new LatLng(mPortalLinkedArray[i].getLat(),mPortalLinkedArray[i].getLong());
        }
        mLinkLine = pMap.addPolyline(new PolylineOptions()
                .add(mLatLngPortalLinkedArray[0], mLatLngPortalLinkedArray[1])
                .width(LINE_WIDTH)
                .color(Color.RED));
    }

}
