package fr.univtln.groupc.activities.map;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univtln.groupc.activities.map.CMapsActivity;
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

    //private Polyline mLinkLine;
    private Polygon mPolygonField;
    private List<CPortalEntity> mPortalLinkedArray;
    private List<CLinkEntity> mLinkArray;
    private LatLng[] mLatLngPortalLinkedArray;
    private Set<CPortalEntity> mSetLatLngPortals;
    private List<CPortalEntity> mListLatLngPortalsForSet;
    private int mColor;


    /**
     * Display a line which represents a link between two portals
     * @param pMap
     * @param pLink
     * @return
     */
    public LatLng[] onDisplayLink(GoogleMap pMap, CLinkEntity pLink) {

        Polyline mLinkLine;
        mPortalLinkedArray = pLink.getPortals();

        // Team color
        Log.d("test", " salut -> " + mPortalLinkedArray.get(0).getTeam());
        if (mPortalLinkedArray.get(0).getTeam() != null){
            if (mPortalLinkedArray.get(0).getTeam().getColor().equals("blue")){
                mColor = Color.BLUE;
            }
            else{
                mColor = Color.RED;
            }
            mLatLngPortalLinkedArray = new LatLng[NB_PORTALS_LINK];
            for (int i = 0; i < NB_PORTALS_LINK; i ++) {
                mLatLngPortalLinkedArray[i] = new LatLng(mPortalLinkedArray.get(i).getLat(), mPortalLinkedArray.get(i).getLong());
            }
            mLinkLine = pMap.addPolyline(new PolylineOptions()
                    .add(mLatLngPortalLinkedArray[0], mLatLngPortalLinkedArray[1])
                    .width(LINE_WIDTH)
                    .color(mColor)); // neutral color*/
          /*  CMapsActivity.mMapPolylines.put(pLink.getId(), pMap.addPolyline(new PolylineOptions()
                    .add(mLatLngPortalLinkedArray[0], mLatLngPortalLinkedArray[1])
                    .width(LINE_WIDTH)
                    .color(mColor)));
*/
            mLinkLine.setVisible(false);

            Log.d("test2", "polyline ajoutee !");
        }

        return mLatLngPortalLinkedArray;
    }

    /**
     * Display a polygon which represents a team field
     * @param pMap
     * @param pField
     * @return
     */
    public LatLng[] onDisplayField(GoogleMap pMap, CFieldEntity pField) {

        mLinkArray = new ArrayList<>();
        mLinkArray.add(pField.getLinks().get(0));
        mLinkArray.add(pField.getLinks().get(1));
        mLinkArray.add(pField.getLinks().get(2));

        mSetLatLngPortals = new HashSet<>();
        mSetLatLngPortals.add(mLinkArray.get(0).getPortals().get(0));
        mSetLatLngPortals.add(mLinkArray.get(0).getPortals().get(1));
        mSetLatLngPortals.add(mLinkArray.get(1).getPortals().get(0));
        mSetLatLngPortals.add(mLinkArray.get(1).getPortals().get(1));
        mSetLatLngPortals.add(mLinkArray.get(2).getPortals().get(0));
        mSetLatLngPortals.add(mLinkArray.get(2).getPortals().get(1));
        mListLatLngPortalsForSet = new ArrayList<>(mSetLatLngPortals);
        mLatLngPortalLinkedArray = new LatLng[NB_PORTALS_FIELD];

        if (mListLatLngPortalsForSet.get(0).getTeam() != null) {
            // Team color
            if (mListLatLngPortalsForSet.get(0).getTeam().getColor().equals("blue")) {
                mColor = Color.BLUE;
            } else {
                mColor = Color.RED;
            }

            for (int i = 0; i < NB_PORTALS_FIELD; i++) {
                mLatLngPortalLinkedArray[i] = new LatLng(mListLatLngPortalsForSet.get(i).getLat(), mListLatLngPortalsForSet.get(i).getLong());
            }

            Polygon lPolygon = pMap.addPolygon(new PolygonOptions()
                    .add(mLatLngPortalLinkedArray)
                    .fillColor(mColor));
        }
        return mLatLngPortalLinkedArray;
    }

}