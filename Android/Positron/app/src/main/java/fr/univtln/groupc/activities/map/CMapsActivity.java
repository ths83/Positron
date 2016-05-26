package fr.univtln.groupc.activities.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CFieldEntity;
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
    private static final int NB_PORTALS_LINK = 2 ;
    private static final float LINE_WIDTH = 5f ;
    private static final int NB_PORTALS_FIELD = 3 ;


    private List<Marker> mResonatorMarkers = new ArrayList<>();
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
    private CPlayerEntity mPlayer;
    private ScrollView mScroll;
    private int mDrawState=0;
    private final List<CPortalEntity> mPortals = new CRestGet().getPortalsRest();
    private LinearLayout mLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mDrawerAction = (DrawerLayout) findViewById(R.id.drawerlayout);
        mScroll = (ScrollView) findViewById(R.id.drawerleft);
        mLinear = (LinearLayout) findViewById(R.id.initaction);
        // TODO singleton for player
        mPlayer = new CRestGet().getPlayerByID(1); // ugly just a test :)
        Log.d("test", "player null ? " + mPlayer);

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
            //Log.d("test", " - > " + p.getLong() + " , " + p.getLat());
            //List<CResonatorEntity> lReso1 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),1);
            //List<CResonatorEntity> lReso2 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),2);
            /*if (p.getId()==7){
                Log.d("test",Integer.toString(lReso1.size()));
                Log.d("test",Integer.toString(lReso1.size()));
            }*/
            //Log.d("test", p.getResonators().toString());
            //Log.d("test", p.getResonators().toString());
//            p.attributeTeam();
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
                tc.setBackground(getResources().getDrawable(R.mipmap.portneutral));
                Bitmap bp = tc.makeIcon(Integer.toString(p.getId()));
                mMap.addMarker(new MarkerOptions()
                        .position(test).title(Integer.toString(p.getId()))
                        .icon(BitmapDescriptorFactory.fromBitmap(bp)));
                //.setSnippet(Integer.toString(p.getId()));
               /* mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        Context context = getApplicationContext();
                        LinearLayout info = new LinearLayout(context);
                        info.setOrientation(LinearLayout.VERTICAL);
                        TextView snip=new TextView(context);
                        snip.setTextColor(Color.BLUE);
                        snip.setText(marker.getSnippet());
                        info.addView(snip);
                        return info;
                    }
                });*/
            } else {
                if (p.getTeam().getColor().equals("blue")) {
                    tc.setBackground(getResources().getDrawable(R.mipmap.portblue));
                    //tc.setTextAppearance(R.style.iconGenText);
                    Bitmap bp = tc.makeIcon(Integer.toString(p.getId()));
                    mMap.addMarker(new MarkerOptions()
                            .position(test).title(Integer.toString(p.getId())).icon(BitmapDescriptorFactory.fromBitmap(bp)));
                }
                if (p.getTeam().getColor().equals("red")) {
                    tc.setBackground(getResources().getDrawable(R.mipmap.portred));
                    //tc.setTextAppearance(R.style.iconGenText);
                    Bitmap bp = tc.makeIcon(Integer.toString(p.getId()));
                    mMap.addMarker(new MarkerOptions()
                            .position(test).title(Integer.toString(p.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(bp)));
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

                double lDistanceBetweenPortalAndPlayer =
                        new CMathFunction().haversine
                                (mPlayer.getLat(), mPlayer.getLong(), marker.getPosition().latitude, marker.getPosition().longitude);

                // Supprime l'affichage des resonateurs d'un autre portail
                // Deletes resonators display from another portal
                for (Marker lMarker : mResonatorMarkers) {
                    lMarker.remove();
                }
                mResonatorMarkers.clear();

                 // Si le portail est dans le rayon d'action d'action du joueur, il peut interagir avec
                 // Players will only interact with portals which are in their radius action
                if (lDistanceBetweenPortalAndPlayer <= GPS_PLAYER_RADIUS) {

                    LatLng lUserLatLng = marker.getPosition();
                    if (mPosition == 1) {
                        if (mDrawState == 1) {
                            mDrawState = 0;
                            mPosition = 0;
                        }
                    }
                    if (mPosition == 0) {
                        CPortalEntity lPortal = new CRestGet().getPortalByIdRest(Integer.parseInt(marker.getTitle()));
                        displayResonators(lPortal.getResonators(), lPortal);
                        mPosition = 1;
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(lUserLatLng));
                        mLinear.removeAllViews();
                        initDrawerAction();
                        mDrawerAction.openDrawer(mScroll);
                        //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                        mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));
                        Log.d("test2", "drawer null ? " + Boolean.toString(mDrawerAction == null));
                        mDrawState = 1;

                        //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.parseInt(marker.getTitle()));

                    }
                    mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            CPortalEntity lPortal = new CRestGet().getPortalByIdRest(Integer.parseInt(marker.getTitle()));
                            marker.setInfoWindowAnchor((float) 1, 1);
                            Context context = getApplicationContext();
                            LinearLayout info = new LinearLayout(context);
                                /*ContextThemeWrapper cw = new ContextThemeWrapper(
                                        getApplicationContext(), R.style.Transparent);
                                // AlertDialog.Builder b = new AlertDialog.Builder(cw);
                                LayoutInflater inflater = (LayoutInflater) cw
                                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                                View layout = inflater.inflate(R.layout.activity_attackportals,
                                        null);
                                info.addView(layout);*/
                            Log.d("test5", "---->" + marker.getTitle());
                            List<CResonatorEntity> lResonatorTeam1 = lPortal.getResonatorsTeamById(1);
                            List<CResonatorEntity> lResonatorTeam2 = lPortal.getResonatorsTeamById(2);
                            int lNbResonatorTeam1 = lResonatorTeam1.size();
                            Log.d("test team blue", Integer.toString(lNbResonatorTeam1));
                            int lNbResonatorTeam2 = lResonatorTeam2.size();
                            Log.d("test team red", Integer.toString(lNbResonatorTeam2));
                            int lNbEmptyPlace = 8 - lNbResonatorTeam1 - lNbResonatorTeam2;
                            //LinearLayout info2 = new LinearLayout(context);
                            info.setOrientation(LinearLayout.VERTICAL);
                            //info2.setOrientation(LinearLayout.HORIZONTAL);
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
                            //mResonatorMarkers
                            return info;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            return null;
                        }
                    });

       /* // Portal action possibilities for players when click on it
                        @Override
                        public View getInfoContents(Marker marker) {
                            Log.d("test",marker.getTitle());
                            List<CResonatorEntity> pResonatorTeam1= new  CRestGet().getResonatorsByPortalAndTeamRest(Integer.parseInt(marker.getTitle()), 1);
                            List<CResonatorEntity> pResonatorTeam2= new  CRestGet().getResonatorsByPortalAndTeamRest(Integer.parseInt(marker.getTitle()), 2);
                            int lNbResonatorTeam1 = pResonatorTeam1.size();
                            Log.d("test",Integer.toString(lNbResonatorTeam1));
                            int lNbResonatorTeam2 = pResonatorTeam2.size();
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
                            return info;
                        }
                    });
                }*/
                }
                return false;

            }


        });

    }


    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            // current position player
            mPlayer.setLat(location.getLatitude());
            mPlayer.setLong(location.getLongitude());
            //location.setBearing(location.getBearing());
            //mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
            mUserLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            //mUserLatLng = new LatLng(mPlayer.getLat(),mPlayer.getLong());
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
                    mUserActionRadius = mMap.addCircle(new CircleOptions().center(mUserLatLng).radius(GPS_PLAYER_RADIUS).fillColor(Color.YELLOW));
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
        //ProgressBar mHealthBar= new ProgressBar();
        //mHealthBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        //mMarkerUserSelected = new LatLng(lLat, lLong);
        //mCircleMarker = mMap.addCircle(new CircleOptions().center(mMarkerUserSelected).radius(pPortal.getRadius()).strokeColor(Color.BLACK));
        double lIdent = -0.0007;
        LatLng lLatLng;
        for (CResonatorEntity lResonator : pResonators) {
            lLatLng = new LatLng(lLat - 0.0004, lLong + lIdent);
            if (lResonator.getOwner().getTeam().getId() == 1) {

                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).title(Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.resblue))));
                Log.d("test5", "title -> " + mResonatorMarkers.get(mResonatorMarkers.size() - 1).getTitle());
            } else {
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).title(Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.resred))));

            }
            lIdent = lIdent + 0.0002;
        }
        int i = pResonators.size();
        while (i < 8) {
            lLatLng = new LatLng(lLat - 0.0004, lLong + lIdent);
            mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                    .position(lLatLng).title(Integer.toString(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.emptyplaceresonator))));
            lIdent = lIdent + 0.0002;
            i = i + 1;
        }

    }

    /*mHealthBar = (ProgressBar)findViewById(R.id.healthbar);

    int lNewHp = mHealthBar.getProgress() - 30;
    int lNewHp = mHealthBar.getProgress() - 30;
    mHealthBar.setProgress(lNewHp);*/
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
        Log.d("test2"," field ? " + mMapPolygonsWithInteger);
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

    public void initDrawerAction(){
        ImageButton lButtonAttack = new ImageButton(this);
        Drawable mDrawable1 = getDrawable(R.mipmap.attack);
        lButtonAttack.setImageDrawable(mDrawable1);
        mLinear.addView(lButtonAttack);
        ImageButton lButtonZone = new ImageButton(this);
        Drawable mDrawable2 = getDrawable(R.mipmap.zoneattack);
        lButtonZone.setImageDrawable(mDrawable2);
        mLinear.addView(lButtonZone);
        ImageButton lButtonCreate = new ImageButton(this);
        Drawable mDrawable3 = getDrawable(R.mipmap.create);
        lButtonCreate.setImageDrawable(mDrawable3);
        mLinear.addView(lButtonCreate);
        ImageButton lButtonBuild = new ImageButton(this);
        Drawable mDrawable4 = getDrawable(R.mipmap.build);
        lButtonBuild.setImageDrawable(mDrawable4);
        mLinear.addView(lButtonBuild);
        ImageButton lButtonPirate = new ImageButton(this);
        Drawable mDrawable5 = getDrawable(R.mipmap.pirate);
        lButtonPirate.setImageDrawable(mDrawable5);
        mLinear.addView(lButtonPirate);
    }


}



