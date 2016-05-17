package fr.univtln.groupc.entities.entities_view;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashSet;
import java.util.Set;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;

/**
 * Created by toms on 5/13/16.
 */
public class CTraceMapView {

    public static final int NB_PORTALS_LINK = 2;
    public static final int NB_PORTALS_FIELD = 3;
    public static final int LINE_WIDTH = 5;

    private Polyline mLinkLine;
    private Polygon mPolygonField;
    private CPortalEntity[] mPortalLinkedArray;
    private CLinkEntity[] mLinkArray;
    private LatLng[] mLatLngPortalLinkedArray;
    private Set<LatLng> mSetLatLngPortals;

    /**
     * Display a line which represents a link between two portals
     *
     * @param pMap
     * @param pLink
     */
    public void onDisplayLink(GoogleMap pMap, CLinkEntity pLink) {

        mPortalLinkedArray = (CPortalEntity[]) pLink.getPortals().toArray();
        mLatLngPortalLinkedArray = new LatLng[NB_PORTALS_LINK];
        for (int i = 0; i < NB_PORTALS_LINK; i++) {
            mLatLngPortalLinkedArray[i] = new LatLng(mPortalLinkedArray[i].getLat(), mPortalLinkedArray[i].getLong());
        }
        mLinkLine = pMap.addPolyline(new PolylineOptions()
                .add(mLatLngPortalLinkedArray[0], mLatLngPortalLinkedArray[1])
                .width(LINE_WIDTH)
                .color(Color.RED));
    }

    /**
     * Display a polygon which represents a team field
     *
     * @param pField
     */
    public Set<LatLng> onDisplayField(CFieldEntity pField) {
        mLinkArray = (CLinkEntity[]) pField.getLinks().toArray();
        mPortalLinkedArray = new CPortalEntity[NB_PORTALS_FIELD];
        for (int i = 0; i < NB_PORTALS_FIELD; i++) {
            mPortalLinkedArray[i] = (CPortalEntity) mLinkArray[i].getPortals();
        }
        // Avoid redunctant LatLng
        mSetLatLngPortals = new HashSet<>();
        for (int j = 0; j < NB_PORTALS_FIELD; j++) {
            mLatLngPortalLinkedArray[j] = new LatLng(mPortalLinkedArray[j].getLat(), mPortalLinkedArray[j].getLong());
            mSetLatLngPortals.add(mLatLngPortalLinkedArray[j]);
        }
        return mSetLatLngPortals;

    }

}