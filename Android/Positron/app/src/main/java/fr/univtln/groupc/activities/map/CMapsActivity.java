package fr.univtln.groupc.activities.map;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.univtln.groupc.activities.portals.CClickPortalsAcitivity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.math.CMathFunction;
import fr.univtln.groupc.rest.CRestDelete;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestUpdate;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CMapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public final static int GPS_PLAYER_RADIUS = 50;
    public final static float MAX_ZOOM_MAP = 17f;
    public static final int NB_PORTALS_LINK = 2 ;
    public static final float LINE_WIDTH = 5f ;
    public static final int NB_PORTALS_FIELD = 3 ;
    public static final String HAVERSINE_BAD_DISTANCE_PORTAL_FRENCH = "Pour interagir avec le portail, celui-ci doit être dans votre rayon d'action.";

    // Toast interaction
    public static final String LINK_PORTAL_NOT_GOOD_TEAM_FRENCH = "Vous ne pouvez pas lier deux portails n'appartenant pas à la même équipe!" ;

    //private ImageButton lButtonZone;
    private CPortalEntity mPortalClicked;
    private Map<Integer, ProgressBar> mProgressBars = new HashMap<>();
    private List<Marker> mResonatorMarkers = new ArrayList<>();
    private List<Marker> mPortalMarkers = new ArrayList<>();
    //private float mZoom = 18;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private CPortalEntity mPortalMarker;
    private BitmapDescriptor mBitmapDescriptor;
    private Circle mCircleMarker;
    private Circle mUserActionRadius;
    private LatLng mLatLng;
    private LatLng mUserLatLng;
    private List<LatLng> mTmpLink = new ArrayList<>();
    private int mPosition = 0;
    private Marker mMarkerPortalAttr;
    private LatLng mMarkerUserSelected;
    private LatLng mLatLng2;

    private List<CResonatorEntity> mClickPortalResonator;
    private List<AObjectEntity> mClickPortalObject;
    private List<CLinkEntity> mClickLink;
    private CFieldEntity mField;
    private String mResonatorString;
    private String mObjectString;
    private String mLinkObjectString;
    private String mFieldString;
    private DrawerLayout mDrawerAction;
    private List<Polyline> mListPolyline = new ArrayList<>();
    private Map<Integer, Polyline> mMapPolylinesWithInteger = new HashMap<>();
    private Map<Integer, Polygon> mMapPolygonsWithInteger = new HashMap<>();
    private Map<CLinkEntity, Polyline> mMapPolylines = new HashMap<>();
    private Map<CFieldEntity, Polygon> mMapPolygons = new HashMap<>();
    private CPlayerEntity mPlayer = new CPlayerEntity();
    private ScrollView mScroll;
    private int mDrawState=0;
    private final List<CPortalEntity> mPortals = new CRestGet().getPortalsRest();
    private LinearLayout mLinear;

    // TODO delete this attr -> just for test link creation
    private CPortalEntity mTestPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mDrawerAction = (DrawerLayout) findViewById(R.id.drawerlayout);
        mScroll = (ScrollView) findViewById(R.id.drawerleft);
        mLinear = (LinearLayout) findViewById(R.id.initaction);

        // TODO test for links
        //Button lTestButton = (Button) findViewById(R.id.link);

        // TODO singleton for player -> with token
        mPlayer = new CRestGet().getPlayerByID(1); // ugly just a test :)
        Log.d("test", "player null ? -> " + mPlayer);

        // Google Map fragment
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button buttonZoomOut = (Button) findViewById(R.id.bzoomout);
        Button buttonZoomIn = (Button) findViewById(R.id.bzoomin);
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMap.getCameraPosition().zoom >= 17.5) {
                    // Zoom like normal
                    mMap.animateCamera(CameraUpdateFactory.zoomOut());
                } else {
                    // Do whatever you want if user went too far
                    Log.d("test", "impossible de dézoomer");
                }
            }

        });

        buttonZoomIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMap.getCameraPosition().zoom < 19) {
                    // Zoom like normal
                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                } else {
                    // Do whatever you want if user went too far
                    Log.d("test", "impossible de zoomer");
                }
            }

        });


        // Location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int [] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Enable some gesture and parameters on map
        mMap = mapFragment.getMap();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


        // Portals from database with REST

        for (CPortalEntity p : mPortals) {
           p.attributeTeam();
            if (p.getId() == 7){
                Log.d("test", "!!!!!!!!!!! PORTAIL QUI DOIT ETRE CHANGE !!!!!!!!!");
            }
            Log.d("test", "team de portail apres attriute team : " + p.getTeam());
            if (p.getTeam() != null) {
                Log.d("test", "OOOOO" + p.getTeam().getColor());
            }
            new CRestUpdate().updatePortalRest(p);
            LatLng test = new LatLng(p.getLat(), p.getLong());
            IconGenerator tc = new IconGenerator(this);
            tc.setTextAppearance(R.style.iconGenText);
            if (p.getTeam() == null) {
                displayPortal(tc,test,p,R.mipmap.portneutral);

            } else {
                if (p.getTeam().getColor().equals("blue")) {
                    displayPortal(tc,test,p,R.mipmap.portblue);

                }
                if (p.getTeam().getColor().equals("red")) {
                    displayPortal(tc,test,p,R.mipmap.portred);
                }
            }
        }

        // Display links on start session
        displayAllLinks();
        displayAllFields();

        // Location Service
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


        // portal created with user click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("test10",Integer.toString(mPosition));
                Log.d("test10",Integer.toString(mDrawState));
                if (mPosition == 1) {
                    if (mDrawState == 1) {
                        mPosition = 0;
                    }
                }
                for (Marker lMarker : mResonatorMarkers) {
                    lMarker.remove();
                }
                mResonatorMarkers.clear();
                //mBitmapDescriptor = BitmapDescriptorFactory.
                //     defaultMarker(BitmapDescriptorFactory.HUE_RED);
                //portalMarker = new CPortalEntity(mMap, latLng, bitmapDescriptor);
                //mTmpLink.add(latLng);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {


            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("test10",Integer.toString(mPosition));
                Log.d("test10",Integer.toString(mDrawState));
                Log.d("test8","------>"+marker.getSnippet());
                if (mPlayer == null){
                    mPlayer = new CPlayerEntity();
                }
                double lDistanceBetweenPortalAndPlayer =
                        new CMathFunction().haversine
                                (mPlayer.getLat(), mPlayer.getLong(), marker.getPosition().latitude, marker.getPosition().longitude);

                // Supprime l'affichage des resonateurs d'un autre portail
                // Deletes resonators display from another portal
                for (Marker lMarker : mResonatorMarkers) {
                    lMarker.remove();
                }
                mResonatorMarkers.clear();

                //String lTitle = marker.getTitle();
                //String[] lNb = lTitle.split(" ");
                //int lI = Integer.parseInt(lNb[1]);

                 // Si le portail est dans le rayon d'action d'action du joueur, il peut interagir avec
                 // Players will only interact with portals which are in their radius action
                Log.d("test","distance -> "  + lDistanceBetweenPortalAndPlayer);
                if (lDistanceBetweenPortalAndPlayer <= GPS_PLAYER_RADIUS) {

                    LatLng lUserLatLng = marker.getPosition();
                    if (mPosition==1){
                        if (mDrawState == 1) {
                            mDrawState = 0;
                            mPosition=0;
                        }
                    }
                    if (mPosition == 0) {
                        String lString = marker.getSnippet();
                        Log.d("test8",lString);
                        String[] lSplitString = lString.split(" ");
                        if (lSplitString[0].equals("portal")) {
                            Log.d("test5", "DANS LE IF DU SPLIT 1");
                            Log.d("test8", "ok " + lSplitString[1]);
                            /*if (mPortalClicked == null){
                                mPortalClicked = new CPortalEntity();
                            }*/
                            mPortalClicked = new CRestGet().getPortalByIdRest(Integer.parseInt(lSplitString[1]));

                            // TODO delete this 382-> test
                            //mTestPortal = lPortal;
                            //Log.d("test8", "portail null ? " + Boolean.toString(lPortal==null));

                            displayResonators(mPortalClicked.getResonators(), mPortalClicked);
                            mPosition = 1;
                        /*mMap.animateCamera(CameraUpdateFactory.newLatLng(lUserLatLng));*/
                            mLinear.removeAllViews();
                            initDrawerAction(mPortalClicked);
                            mDrawerAction.openDrawer(mScroll);
                            //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                            mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));
                            Log.d("tesmPlayert2", "drawer null ? " + Boolean.toString(mDrawerAction == null));
                            mDrawState = 1;
                        }

                        //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.parseInt(marker.getSnippet()));

                    else if (lSplitString[0].equals("resonator")){
                        Log.d("test5", "click sur reso");
                    }

                    //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.parseInt(marker.getSnippet()));

                    }

                }
                else{
                    Toast.makeText(getBaseContext(),HAVERSINE_BAD_DISTANCE_PORTAL_FRENCH,Toast.LENGTH_SHORT).show();
                }
                return false;

            }
        });



    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            // current position player
            //mPlayer.setLat(location.getLatitude());
            //mPlayer.setLong(location.getLongitude());
            //location.setBearing(location.getBearing());
            //mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
            //mUserLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            /*if (mPlayer == null){
                mPlayer = new CPlayerEntity();
            }*/
            mPlayer.setLat(location.getLatitude());
            mPlayer.setLong(location.getLongitude());
            //mUserLatLng = new LatLng(mPlayer.getLat(),mPlayer.getLong());
            mUserLatLng = new LatLng(mPlayer.getLat(),mPlayer.getLong());
            if (mPosition != 1) {
                //mMap.animateCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
            }
                //Log.i("test", "Latitude: "+String.valueOf(location.getLatitude())+" - Longitude: "+String.valueOf(location.getLongitude()));
                //mMap.moveCamera(CameraUpdateFactory.zoomTo(8f));
                if (mUserActionRadius != null) {
                    mUserActionRadius.setCenter(mUserLatLng);
                } else {
                    mUserActionRadius = mMap.addCircle(new CircleOptions().center(mUserLatLng).radius(GPS_PLAYER_RADIUS).fillColor(Color.TRANSPARENT));
                }

            }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    // user action radius when new position is detected
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        // gps enable
        Toast.makeText(getBaseContext(), GPS_ON_FRENCH, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        // player needs to active gps sensor
        Toast.makeText(getBaseContext(), GPS_OFF_FRENCH, Toast.LENGTH_SHORT).show();
        // if gps is disabled gps settings will be opened to enable it
        Intent enableGpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(enableGpsIntent);
    }


    /*
     * Affiche les icones de tous les resonateurs
     * d'un portail lors d'un clic sur l'icone de
     * ce dernier.
     *
     * ------
     *
     * Displays all the icons of all the resonators
     * from a portail given when being clicked.
     */
    public void displayResonators(List<CResonatorEntity> pResonators, CPortalEntity pPortal) {
        double lLat = pPortal.getLat();
        double lLong = pPortal.getLong();
        double lIdent = -0.0007;
        LatLng lLatLng;
        for (CResonatorEntity lResonator : pResonators) {
            lLatLng = new LatLng(lLat - 0.0004, lLong + lIdent);
            IconGenerator tc = new IconGenerator(this);
            tc.setTextAppearance(R.style.iconGenText);
            if (lResonator.getOwner().getTeam().getId() == 1) {
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).snippet("resonator " + Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromBitmap(displayResonatorWithEnergy(tc,R.mipmap.resblue,lResonator)))));
                Log.d("test5", "snippet -> " + mResonatorMarkers.get(mResonatorMarkers.size() - 1).getSnippet());
            } else {
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).snippet("resonator " + Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromBitmap(displayResonatorWithEnergy(tc,R.mipmap.resred,lResonator)))));

            }
            lIdent = lIdent + 0.0002;
        }
        int i = pResonators.size();
        while (i < 8) {
            lLatLng = new LatLng(lLat - 0.0004, lLong + lIdent);
            mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                    .position(lLatLng).snippet("resonator empty")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.emptyplaceresonator))));
            lIdent = lIdent + 0.0002;
            i = i + 1;
        }

    }

    //just a test
    public void onClickTest(View pView){
        Log.d("test2", "etat de la hashmap :\n" + mMapPolylines);
        //TODO recuperer id du portail clique
        //TODO gets on click portal id
        CLinkEntity lLink = new CRestGet().getLinkByID(10); // test
        deleteLinkInGoogleMapAndHashMap(lLink);
    }


    /*
     * Supprime un lien donné (via son id) a la fois
     * de la hashmap, de la map google et de la
     * base de donnees.
     *
     * -----
     *
     * Deletes a given link from the
     * hashmap, googlemap and database
     */
    public void deleteLinkInGoogleMapAndHashMap(CLinkEntity pLink){
        Log.d("test2", "hashmap => " + mMapPolylinesWithInteger);
        Polyline lPolylineToRemove =  mMapPolylinesWithInteger.get(pLink.getId());
        Log.d("test2", "\n\n\n SALUT LE POLYLINE  : " + lPolylineToRemove);
        lPolylineToRemove.remove();
        //mMapPolylines.remove(pLink);
        mMapPolylinesWithInteger.remove(pLink.getId());
        if (pLink.getField() != null){
            CFieldEntity lFieldToDelete = pLink.getField();
            List<CLinkEntity> lLinkFromField = pLink.getField().getLinks();
            for (CLinkEntity lL : lLinkFromField){
                lL.setField(null);
                new CRestUpdate().updateLinkRest(lL);
            }
            deleteFieldInGoogleMapAndHashMap(lFieldToDelete);
        }
        new CRestDelete().deleteLinkRest(pLink.getId());
    }


    /*
     * Supprime un champ donné a la fois de
     * la hashmap et de la googlemap.
     *
     * ------
     *
     * Deletes a given field from both the
     * hashmap and the googlemap
     */
    public void deleteFieldInGoogleMapAndHashMap(CFieldEntity pField){
        Log.d("test2", " field ? " + mMapPolygonsWithInteger);
        if (mMapPolygonsWithInteger.containsKey(pField.getId())){
            mMapPolygonsWithInteger.get(pField.getId()).remove();
            mMapPolygonsWithInteger.remove(pField);
            // TODO test
            Log.d("test3", "Field !? " + pField);

            Log.d("test3", "Field !? " + pField);
        }
        else{
            //customiser erreur
            Log.d("test2", "field a delete pas stocké");
        }
    }


    /*
     * Affiche tous les liens des portails de la google map.
     * Ne crée pas 2 fois le même lien entre 2 portails.
     *
     * -------
     *
     * Displays all the links from portals on the google map.
     * Doesn't create the same link twice between 2 portals.
    */
    public void displayAllLinks(){
        Set<CLinkEntity> lSetLink = new HashSet<>();
        int lI = 0;
        for (CPortalEntity lPortal : mPortals) {
            for (lI = 0; lI < lPortal.getLinks().size(); lI++) {
                lSetLink.add(lPortal.getLinks().get(lI));
            }
        }
        for (CLinkEntity lLink : lSetLink){
            onDisplayLink(lLink);
        }
    }

    /*
     * Affiche tous les fields sur la google map.
     *
     * ------
     *
     * Displays all the fields on the google map.
     */
    public void displayAllFields() {
        Set<CFieldEntity> lSetField = new HashSet<>();
        int lI = 0;
        for (CPortalEntity lPortal : mPortals) {
            for (lI = 0; lI < lPortal.getLinks().size(); lI++) {
                if (lPortal.getLinks().get(lI).getField() != null) {
                    lSetField.add(lPortal.getLinks().get(lI).getField());
                }
            }
        }
        for (CFieldEntity lField : lSetField) {
            onDisplayField(lField);
        }
    }

    /**
     * methode permettant de tracer un polygone
     * representant un champ, en recuperant les coords des portails
     * le delimitant
     *
     * -----
     *
     * Traces a polygon which represents a field, with portals LatLng.
     *
     * @param pField
     */
    public void onDisplayField(CFieldEntity pField) {

        List<CLinkEntity> lLinkArray = new ArrayList<>();
        lLinkArray.add(pField.getLinks().get(0));
        lLinkArray.add(pField.getLinks().get(1));
        lLinkArray.add(pField.getLinks().get(2));

        // Avoid redunctant LatLng for field
        Set<CPortalEntity> lSetLatLngPortals = new HashSet<>();
        lSetLatLngPortals.add(lLinkArray.get(0).getPortals().get(0));
        lSetLatLngPortals.add(lLinkArray.get(0).getPortals().get(1));
        lSetLatLngPortals.add(lLinkArray.get(1).getPortals().get(0));
        lSetLatLngPortals.add(lLinkArray.get(1).getPortals().get(1));
        lSetLatLngPortals.add(lLinkArray.get(2).getPortals().get(0));
        lSetLatLngPortals.add(lLinkArray.get(2).getPortals().get(1));

        List<CPortalEntity> lListLatLngPortalsForSet = new ArrayList<>(lSetLatLngPortals);
        LatLng[] lLatLngPortalLinkedArray = new LatLng[NB_PORTALS_FIELD];

        if (lListLatLngPortalsForSet.get(0).getTeam() != null) {
            // Team color
            int lTeamColor;
            if (lListLatLngPortalsForSet.get(0).getTeam().getColor().equals("blue")) {
                lTeamColor = Color.BLUE;
            } else {
                lTeamColor = Color.RED;
            }

            for (int i = 0; i < NB_PORTALS_FIELD; i++) {
                lLatLngPortalLinkedArray[i] = new LatLng(lListLatLngPortalsForSet.get(i).getLat(), lListLatLngPortalsForSet.get(i).getLong());
            }

            Polygon lPolygon = mMap.addPolygon(new PolygonOptions()
                    .add(lLatLngPortalLinkedArray)
                    .fillColor(lTeamColor));

            mMapPolygonsWithInteger.put(pField.getId(),lPolygon);
        }
    }


    /*
     * Trace un lien sur la map en lui
     * appliquant la couleur correspondant
     * a son equipe.
     *
     * ------
     *
     * Traces a link on the google map
     * applies the right color matching
     * his team.
     */
    public void onDisplayLink(CLinkEntity pLink) {

        Polyline lLinkLine;
        List<CPortalEntity> lPortalLinkedArray = pLink.getPortals();

        LatLng[] lLatLngPortalLinkedArray = new LatLng[NB_PORTALS_LINK];
        if (lPortalLinkedArray.get(0).getTeam() != null){
            int lTeamColor;
            if (lPortalLinkedArray.get(0).getTeam().getColor().equals("blue")){
                lTeamColor = Color.BLUE;
            }
            else{
                lTeamColor = Color.RED;
            }
            for (int i = 0; i < NB_PORTALS_LINK; i ++) {
                lLatLngPortalLinkedArray[i] = new LatLng(lPortalLinkedArray.get(i).getLat(), lPortalLinkedArray.get(i).getLong());
            }

            lLinkLine = mMap.addPolyline(new PolylineOptions()
                    .add(lLatLngPortalLinkedArray[0], lLatLngPortalLinkedArray[1])
                    .width(LINE_WIDTH)
                    .color(lTeamColor));

            // ajout dans la hashmap pour pouvoir le supprimer facilement via le lien auquel il correspond
            // adding in the hashmap in order to delete it easily with the link to whom he belongs.
            mMapPolylinesWithInteger.put(pLink.getId(), lLinkLine);
        }
    }


    /**
     * recuperer la cle possede par le joueur pour creer un lien avec le portail clique
     * @param pView
     */
    // TODO first version -> to modify fastly
    public void onCreateLink(View pView){
        int lTeamColor;
        String lStringTeamColor;
        // Link color
        if (mPlayer.equals("blue")){
            lTeamColor = Color.BLUE;
            lStringTeamColor = "BLUE";
        }
        else{
            lTeamColor = Color.RED;
            lStringTeamColor = "RED";
        }

        // just for the first key
        List<CKeyEntity> lPlayerKeysForPortal = mPlayer.getKeys();
        CKeyEntity lKey = lPlayerKeysForPortal.get(0);
        String lStringPortalTeamColor = "NONE" ;
        if (lPlayerKeysForPortal.get(0).getPortal().getTeam() != null){
            lStringPortalTeamColor = lPlayerKeysForPortal.get(0).getPortal().getTeam().getColor();
        }
       // Log.d("test","team color -> " + lStringPortalTeamColor);

        if (lStringPortalTeamColor.equals(lStringTeamColor)){
            LatLng lBeginningPortal = new LatLng(lKey.getPortal().getLat(),lKey.getPortal().getLong());
            LatLng lLastPortal = new LatLng(mTestPortal.getLat(),mTestPortal.getLong());
            Polyline lLinkToDraw = mMap.addPolyline(new PolylineOptions()
                    .add(lBeginningPortal, lLastPortal)
                    .width(LINE_WIDTH)
                    .color(lTeamColor));
        }
        else {
            Toast.makeText(getBaseContext(),LINK_PORTAL_NOT_GOOD_TEAM_FRENCH,Toast.LENGTH_SHORT).show();
        }

    }


    /*
     * Actions disponibles du "drawer
     * action" après un click
     * sur un portail (étape 1)
     *
     * ------
     *
     * Actions of "Drawer Action"
     * after portal's click
     * (step 1)
     */

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initDrawerAction(final CPortalEntity pPortal){

        ImageButton lButtonAttack = new ImageButton(this);
        Drawable mDrawable1 = getDrawable(R.mipmap.attack);
        lButtonAttack.setImageDrawable(mDrawable1);
        mLinear.addView(lButtonAttack);
        /*lButtonAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        ImageButton lButtonZone = new ImageButton(this);
        Log.d("test5","---->"+lButtonZone.isEnabled());
        lButtonZone.setEnabled(true);
        if (mPlayer.getBombs().size()>0) {
            Drawable mDrawable2 = getDrawable(R.mipmap.zoneattackvalid);
            lButtonZone.setImageDrawable(mDrawable2);
            lButtonZone.setClickable(true);
        }
        else {
            Drawable mDrawable2 = getDrawable(R.mipmap.zoneattack);
            lButtonZone.setImageDrawable(mDrawable2);
            lButtonZone.setClickable(false);
        }
        mLinear.addView(lButtonZone);
        lButtonZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CPortalEntity lPortal;
                Log.d("test5", "dans le onclick !");
                CClickPortalsAcitivity.useBombe(mPlayer.getBombs().get(0), pPortal, mPlayer);
                /*for (CResonatorEntity lResonator : lPortal.getResonators()){
                    Log.d("test15", "ds la map de progressbar ? " + mProgressBars.get(lResonator.getId()));
                    mProgressBars.get(lResonator.getId()).setProgress(lResonator.getEnergy());
                    Log.d("test15", "progress apres degat! " + mProgressBars.get(lResonator.getId()).getProgress());

                }*/

                Log.d("test5", "rte" + pPortal.getResonators().get(0).getEnergy());
            }
        });
        ImageButton lButtonCreate = new ImageButton(this);
        Drawable mDrawable3 = getDrawable(R.mipmap.create);
        lButtonCreate.setImageDrawable(mDrawable3);
        mLinear.addView(lButtonCreate);
        ImageButton lButtonBuild = new ImageButton(this);
        Drawable mDrawable4 = getDrawable(R.mipmap.build);
        lButtonBuild.setImageDrawable(mDrawable4);
        lButtonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                reparationDrawerExtend(mPortalMarker);
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));
            }
        });
        mLinear.addView(lButtonBuild);
        ImageButton lButtonPirate = new ImageButton(this);
        Drawable mDrawable5 = getDrawable(R.mipmap.pirate);
        lButtonPirate.setImageDrawable(mDrawable5);
        mLinear.addView(lButtonPirate);
        ImageButton lButtonCancel = new ImageButton(this);
        Drawable mDrawable6 = getDrawable(R.mipmap.cancel);
        lButtonCancel.setImageDrawable(mDrawable6);
        mLinear.addView(lButtonCancel);
    }


    /*
     * Affiche les résonateurs avec leur
     * niveau de vie et leur équipe
     *
     *
     * ------
     *
     * Displays resonators with their
     * energy and team color
     *
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Bitmap displayResonatorWithEnergy(IconGenerator pIg,int pId,CResonatorEntity pResonator){
        pIg.setBackground(null);
        Context lContext = getApplicationContext();
        LinearLayout lLinearLayout = new LinearLayout(lContext);
        lLinearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView lImage = new ImageView(lContext);
        LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(90, 90);
        lImage.setLayoutParams(lParams1);
        lImage.setImageDrawable(getResources().getDrawable(pId));
        lLinearLayout.addView(lImage);
        ProgressBar lHealth = new ProgressBar(lContext, null ,android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams lParams2 = new LinearLayout.LayoutParams(100, 10);
        lHealth.setLayoutParams(lParams2);
        lHealth.setMax(pResonator.getEnergyMax());
        lHealth.setProgress(pResonator.getEnergy());
        lHealth.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        mProgressBars.put(pResonator.getId(), lHealth);
        lLinearLayout.addView(lHealth);
        pIg.setContentView(lLinearLayout);
        Bitmap bp = pIg.makeIcon();
        return bp;
    }

    public void displayPortal(IconGenerator pIg,LatLng pLatLng, CPortalEntity pPortal, int pId){
        pIg.setBackground(getResources().getDrawable(pId));
        List<CResonatorEntity> lResonatorTeam1 = pPortal.getResonatorsTeamById(1);
        List<CResonatorEntity> lResonatorTeam2 = pPortal.getResonatorsTeamById(2);
        int lNbResonatorTeam1 = lResonatorTeam1.size();
        Log.d("test",Integer.toString(lNbResonatorTeam1));
        int lNbResonatorTeam2 = lResonatorTeam2.size();
        Log.d("test",Integer.toString(lNbResonatorTeam2));
        int lNbEmptyPlace = 8 - lNbResonatorTeam1 - lNbResonatorTeam2;
        Context context = getApplicationContext();
        LinearLayout info = new LinearLayout(context);
        info.setOrientation(LinearLayout.VERTICAL);
        TextView snip1 = new TextView(context);
        snip1.setTextColor(Color.BLUE);
        snip1.setText(Integer.toString(lNbResonatorTeam1));
        info.addView(snip1);
        TextView snip2 = new TextView(context);
        snip2.setTextColor(Color.RED);
        snip2.setText(Integer.toString(lNbResonatorTeam2));
        info.addView(snip2);
        TextView snip3 = new TextView(context);
        snip3.setTextColor(Color.BLACK);
        snip3.setText(Integer.toString(lNbEmptyPlace));
        info.addView(snip3);
        pIg.setContentView(info);
        Bitmap bp = pIg.makeIcon(Integer.toString(pPortal.getId()));
        mPortalMarkers.add(mMap.addMarker(new MarkerOptions()
                .position(pLatLng).snippet("portal " + Integer.toString(pPortal.getId()))
                .icon(BitmapDescriptorFactory.fromBitmap(bp))));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void reparationDrawerExtend(final CPortalEntity pPortal) {

        ImageButton lButtonCreate = new ImageButton(this);
        Drawable mDrawable1 = getDrawable(R.mipmap.keyportal);
        lButtonCreate.setImageDrawable(mDrawable1);
        mLinear.addView(lButtonCreate);
        ImageButton lButtonBuild = new ImageButton(this);
        Drawable mDrawable2 = getDrawable(R.mipmap.virus);
        lButtonBuild.setImageDrawable(mDrawable2);
        mLinear.addView(lButtonBuild);
        ImageButton lButtonPirate = new ImageButton(this);
        Drawable mDrawable3 = getDrawable(R.mipmap.kitsoin);
        lButtonPirate.setImageDrawable(mDrawable3);
        mLinear.addView(lButtonPirate);
    }



}



