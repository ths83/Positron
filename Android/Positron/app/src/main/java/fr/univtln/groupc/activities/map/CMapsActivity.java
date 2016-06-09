package fr.univtln.groupc.activities.map;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.univtln.groupc.CAttackBuilding;
import fr.univtln.groupc.CCreateLink;
import fr.univtln.groupc.CHackLimitation;
import fr.univtln.groupc.CHackPortal;
import fr.univtln.groupc.CHackPortalKey;
import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.CPoseBuilding;
import fr.univtln.groupc.CPoseResonator;
import fr.univtln.groupc.CPoseVirus;
import fr.univtln.groupc.EPayloadType;
import fr.univtln.groupc.activities.google.SCurrentPlayer;
import fr.univtln.groupc.activities.portals.CClickPortalsAcitivity;
import fr.univtln.groupc.activities.profil.CChoiceActivity;
import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CLinkImprovementEntity;
import fr.univtln.groupc.entities.CMultiHackEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;
import fr.univtln.groupc.entities.CTurretEntity;
import fr.univtln.groupc.math.CMathFunction;
import fr.univtln.groupc.rest.CRestDelete;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestUpdate;
import fr.univtln.groupc.wsclient.CMessageHandler;
import fr.univtln.groupc.wsclient.CTyrusClient;
import fr.univtln.groupc.wsclient.CWebSocketService;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public final static int GPS_PLAYER_RADIUS = 300;
    public final static float MAX_ZOOM_MAP = 17f;
    public static final int NB_PORTALS_LINK = 2;
    public static final float LINE_WIDTH = 5f;
    public static final int NB_PORTALS_FIELD = 3;
    public static final String HAVERSINE_BAD_DISTANCE_PORTAL_FRENCH = "Pour interagir avec le portail, celui-ci doit être dans votre rayon d'action.";

    // Toast interaction
    public static final String LINK_PORTAL_NOT_GOOD_TEAM_FRENCH = "Vous ne pouvez pas lier deux portails n'appartenant pas à la même équipe!";

    //private ImageButton lButtonZone;
    private CPortalEntity mPortalClicked;
    private Map<Integer, ProgressBar> mProgressBars = new HashMap<>();
    private List<Marker> mResonatorMarkers = new ArrayList<>();
    private List<Marker> mPortalMarkers = new ArrayList<>();
    private Map<Integer, Marker> mPortalMarkersHashMap = new HashMap<>();
    private Map<Integer, Marker> mResonatorMarkersHashMap = new HashMap<>();
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
    //private CPlayerEntity mPlayer = new CRestGet().getPlayerByID(1);
    private List<CPortalEntity> mPortals = new CRestGet().getPortalsRest();
    private HorizontalScrollView mScroll;
    private int mDrawState = 0;
    private LinearLayout mLinear;
    private BroadcastReceiver mBroadCastReceiverWS;
    private List<Integer> ColorMunition= Arrays.asList(Color.BLACK, R.color.brown, null, R.color.gold);
    private CConsumableEntity mMunition;
    public ProgressBar mHealthPlayer;
    public ProgressBar mXp;

    // TODO delete this attr -> just for test link creation
    private CPortalEntity mTestPortal;

    // TODO add multiple buttons for each subMenu
    //private FloatingActionButton mMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //mMenuButton = (FloatingActionButton) findViewById(R.id.menu_button);


        Intent lIntent = new Intent(CMapsActivity.this, CWebSocketService.class);
        startService(lIntent);

        // Launcher service
        /*
        Intent lLauncherIntent = new Intent(this, CLaunchService.class);
        Intent lPodoIntent = new Intent(this, CPodometerService.class);
        startService(lLauncherIntent);
        startService(lPodoIntent);
*/
        mBroadCastReceiverWS = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent pIntent) {

                Log.d("tag", "DANS ON RECEIVE");
                if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.RESONATOR_POSED.toString())) {
                    Log.d("tag", "premier if pas else");
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(CMessageHandler.PORTAL);
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    Log.d("tag", "portal recupere : " + lPortal);
                    replacePortal(lPortal);
                    SCurrentPlayer.mPlayer = lPlayer;
                } else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())) {
                    Log.d("tag", "ok jusque la");
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(CMessageHandler.PORTAL);
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    Log.d("tag", "portal recupere : " + lPortal);
                    replacePortal(lPortal);
                    SCurrentPlayer.mPlayer = lPlayer;

                }
                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.BUILDING_ATTACKED.toString())) {
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(CMessageHandler.PORTAL);
                    mPortalClicked = lPortal;
                    replacePortal(lPortal);
                    SCurrentPlayer.mPlayer = lPlayer;
                }


                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.LINK_CREATED.toString())){
                    CLinkEntity lLink = (CLinkEntity) pIntent.getSerializableExtra(CMessageHandler.LINK);
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    displayLink(lLink);
                    SCurrentPlayer.mPlayer = lPlayer;
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.FIELD_CREATED.toString())){
                    CLinkEntity lLink = (CLinkEntity) pIntent.getSerializableExtra(CMessageHandler.LINK);
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    Log.d("test78", "field null ? -> " + Boolean.toString(lLink.getField() == null));
                    CFieldEntity lField = lLink.getField();
                    displayLink(lLink);
                    displayField(lField);
                    SCurrentPlayer.mPlayer = lPlayer;
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.VIRUS_POSED.toString())){
                    //List<Integer> lLinkIds =
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.PORTAL_HACKED.toString())){
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    SCurrentPlayer.mPlayer = lPlayer;
                    Toast.makeText(getBaseContext(), "hack reussi", Toast.LENGTH_SHORT).show();
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.PORTAL_KEY_HACKED.toString())){
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    SCurrentPlayer.mPlayer = lPlayer;
                    Toast.makeText(getBaseContext(), "clef recup", Toast.LENGTH_SHORT).show();
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.HACK_LIMITATION.toString())){
                    String lTimeLeft = ((CHackLimitation) pIntent.getSerializableExtra(CMessageHandler.HACK_LIMITATION)).getDuration();
                    Log.d("test150", "tps restant " + lTimeLeft);
                    Toast.makeText(getBaseContext(), "hack trop recents, attendez " + lTimeLeft + "secondes", Toast.LENGTH_SHORT).show();
                }

                else if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.AOE_ATTACKED.toString())){
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(CMessageHandler.PORTAL);
                }
                if (pIntent.getStringExtra(CMessageHandler.TYPE).equals(EPayloadType.RESONATOR_POSED.toString())) {
                    Log.d("tag", "premier if pas else");
                    CPortalEntity lPortal = (CPortalEntity) pIntent.getSerializableExtra(CMessageHandler.PORTAL);
                    CPlayerEntity lPlayer = (CPlayerEntity) pIntent.getSerializableExtra(CMessageHandler.PLAYER);
                    Log.d("tag", "portal recupere : " + lPortal);
                    replacePortal(lPortal);
                    SCurrentPlayer.mPlayer = lPlayer;
                }


            }
        };
        registerReceiver(mBroadCastReceiverWS,new IntentFilter(CMessageHandler.INTENT_TYPE));

        mHealthPlayer = (ProgressBar) findViewById(R.id.health_player);
        mXp = (ProgressBar) findViewById(R.id.xp);
        mDrawerAction = (DrawerLayout) findViewById(R.id.drawerlayout);
        mScroll = (HorizontalScrollView) findViewById(R.id.drawerleft);
        mLinear = (LinearLayout) findViewById(R.id.initaction);
        SCurrentPlayer.mPlayer = new CRestGet().getPlayerByID(1);
        setHealth();
        setXp();

        // TODO test for links
        //Button lTestButton = (Button) findViewById(R.id.link);

        // TODO singleton for player -> with token
        // mPlayer = new CRestGet().getPlayerByID(1); // ugly just a test :)
        //Log.d("test", "player null ? -> " + mPlayer);

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
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


        // Portals from database with REST

        for (CPortalEntity p : mPortals) {
            p.attributeTeam();
            if (p.getId() == 7) {
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
                displayPortal(tc, test, p, R.mipmap.portneutral);

            } else {
                if (p.getTeam().getColor().equals("blue")) {
                    displayPortal(tc, test, p, R.mipmap.portblue);

                }
                if (p.getTeam().getColor().equals("red")) {
                    displayPortal(tc, test, p, R.mipmap.portred);
                }
            }

        }

        // Display links on start session
        displayAllLinks();
        displayAllFields();
        Log.d("test", "player null 2? -> " + SCurrentPlayer.mPlayer);
        // Location Service
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


        // portal created with user click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("test10", Integer.toString(mPosition));
                Log.d("test10", Integer.toString(mDrawState));
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
                Log.d("test10", Integer.toString(mPosition));
                Log.d("test10", Integer.toString(mDrawState));
                Log.d("test8", "------>" + marker.getSnippet());
                /*if (mPlayer == null){
                    mPlayer = new CPlayerEntity();
                }*/
                double lDistanceBetweenPortalAndPlayer =
                        new CMathFunction().haversine
                                (SCurrentPlayer.mPlayer.getLat(), SCurrentPlayer.mPlayer.getLong(), marker.getPosition().latitude, marker.getPosition().longitude);

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
                Log.d("test", "distance -> " + lDistanceBetweenPortalAndPlayer);
                if (lDistanceBetweenPortalAndPlayer <= GPS_PLAYER_RADIUS) {

                    LatLng lUserLatLng = marker.getPosition();
                    if (mPosition == 1) {
                        if (mDrawState == 1) {
                            mDrawState = 0;
                            mPosition = 0;
                        }
                    }
                    if (mPosition == 0) {
                        String lString = marker.getSnippet();
                        Log.d("test8", lString);
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
                            mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, mScroll);
                            displayResonators(mPortalClicked, 0);
                            displayBuildings(0);
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

                        else if (lSplitString[0].equals("resonator")) {
                            CAttackBuilding lAttack = new CAttackBuilding.CAttackBuildingBuilder().consumableId(mMunition.getId()).buildingId(Integer.parseInt(lSplitString[1])).playerId(SCurrentPlayer.mPlayer.getId()).build();
                            CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.ATTACK_BUILDING.toString()).objectAttackBuilding(lAttack).build();
                            CTyrusClient.sendMessage(lBean);
                            //Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                            //CTyrusClient.sendMessage(lBean);
                            //mLinear.removeAllViews();
                            mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, mScroll);
                            mLinear.removeAllViews();
                            initDrawerAction(mPortalClicked);
                            mDrawerAction.openDrawer(mScroll);
                            mPosition = 1;
                            mDrawState = 1;
                        }

                        //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.parseInt(marker.getSnippet()));

                    }

                } else {
                    Toast.makeText(getBaseContext(), HAVERSINE_BAD_DISTANCE_PORTAL_FRENCH, Toast.LENGTH_SHORT).show();
                }
                return false;

            }
        });


    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            // current position player
            SCurrentPlayer.mPlayer.setLat(location.getLatitude());
            SCurrentPlayer.mPlayer.setLong(location.getLongitude());

//            SCurrentPlayer.mPlayer.setLat(location.getLatitude());
            //          SCurrentPlayer.mPlayer.setLong(location.getLongitude());


//            mPlayer.setLat(location.getLatitude());
            //          mPlayer.setLong(location.getLongitude());

            //location.setBearing(location.getBearing());
            //mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
            //mUserLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            /*if (mPlayer == null){
                mPlayer = new CPlayerEntity();
            }*/
            //mPlayer = new CPlayerEntity.CPlayerBuilder(1).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            //mPlayer.setLat(location.getLatitude());
            //mPlayer.setLong(location.getLongitude());
            //mUserLatLng = new LatLng(mPlayer.getLat(),mPlayer.getLong());
            mUserLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (mPosition != 1) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
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

    @Override
    public void onLocationChanged(Location location) {
        //TODO Envoie Un player au serveur pour appeler CAlgorithm.iNField()

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

    public void displayResonators(CPortalEntity pPortal, int pState) {
        List<CResonatorEntity> lResonators = mPortalClicked.getResonators();
        double lEcart = 0.0002;
        List<Double> lLatitudes = latitudeResonators(mPortalClicked.getLat(), lEcart);
        List<Double> lLongitudes = longitudeResonators(mPortalClicked.getLong(), lEcart);
        double lLat = pPortal.getLat();
        double lLong = pPortal.getLong();
        //double lIdent = -0.0007;
        LatLng lLatLng;
        IconGenerator tc = new IconGenerator(this);
        int cpt = 0;
        //tc.setTextAppearance(R.style.iconGenText);
        for (CResonatorEntity lResonator : lResonators) {
            //lLatLng = new LatLng(lLat - 0.0004, lLong + lIdent);
            lLatLng = new LatLng(lLatitudes.get(cpt), lLongitudes.get(cpt));
            if (lResonator.getOwner().getTeam().getId() == 1) {
                if (pState!=1) {
                    Marker lMarker = mMap.addMarker(new MarkerOptions()
                            .position(lLatLng).snippet("resonator " + Integer.toString(lResonator.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(displayElementWithEnergy(tc, R.mipmap.resblue, lResonator.getEnergy(), lResonator.getEnergyMax()))));
                    mResonatorMarkers.add(lMarker);
                    mResonatorMarkersHashMap.put(lResonator.getId(), lMarker);
                    Log.d("test5", "snippet -> " + mResonatorMarkers.get(mResonatorMarkers.size() - 1).getSnippet());
                }
            } else {
                if (pState!=2) {
                    Marker lMarker = mMap.addMarker(new MarkerOptions()
                            .position(lLatLng).snippet("resonator " + Integer.toString(lResonator.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(displayElementWithEnergy(tc, R.mipmap.resred, lResonator.getEnergy(), lResonator.getEnergyMax()))));
                    mResonatorMarkers.add(lMarker);
                    mResonatorMarkersHashMap.put(lResonator.getId(), lMarker);
                }
            }
            cpt=cpt+1;
            //lIdent = lIdent + 0.0002;
        }
        int i = lResonators.size();
        Log.d("ok", "-->" + i);
        if (pState==0) {
            while (i < 8) {
                lLatLng = new LatLng(lLatitudes.get(i), lLongitudes.get(i));
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).snippet("resonatorplace empty")
                        .icon(BitmapDescriptorFactory.fromBitmap(displayEmptyPlace(tc, R.mipmap.resneutral)))));
                //fromResource(R.mipmap.emptyplaceresonator)
                i = i + 1;
            }
        }

    }


    public void displayBuildings(int pState) {
        double lEcart = 0.0002;
        List<Double> lLatitudes = latitudeBuildings(mPortalClicked.getLat(), lEcart);
        List<Double> lLongitudes = longitudeBuildings(mPortalClicked.getLong(), lEcart);
        LatLng lLatLng;
        IconGenerator tc = new IconGenerator(this);
        List<ABuildingEntity> lBuildings = mPortalClicked.getBuildings();
        Log.d("pogba10","->"+lBuildings);
        int cpt =0;
        for (ABuildingEntity lBuilding : lBuildings) {
            if (!(lBuilding instanceof CResonatorEntity)) {
                lLatLng = new LatLng(lLatitudes.get(cpt), lLongitudes.get(cpt));
                if (lBuilding.getPortal().getTeam().getId() == 1) {
                    if (pState != 1) {
                    /*Marker lMarker = mMap.addMarker(new MarkerOptions()
                            .position(lLatLng).snippet("building " + Integer.toString(lBuilding.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(displayElementWithEnergy(tc, R.mipmap.resred, lBuilding.getEnergy(), lBuilding.getEnergyMax()))));*/
                    /*mResonatorMarkers.add(lMarker);
                    mResonatorMarkersHashMap.put(lResonator.getId(), lMarker);*/
                    }
                }
                if (lBuilding.getPortal().getTeam().getId() == 2) {
                    if (pState != 2) {
                    /*Marker lMarker = mMap.addMarker(new MarkerOptions()
                            .position(lLatLng).snippet("building " + Integer.toString(lBuilding.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(displayElementWithEnergy(tc, R.mipmap.resred, lBuilding.getEnergy(),lBuilding.getEnergyMax()))));*/
                    }
                }
                cpt = cpt + 1;
            }
        }
        Log.d("juve", lBuildings.size() + "<--");
        if (pState==0) {
            while (cpt < 4) {
                lLatLng = new LatLng(lLatitudes.get(cpt), lLongitudes.get(cpt));
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(lLatLng).snippet("building empty")
                        .icon(BitmapDescriptorFactory.fromBitmap(displayEmptyPlace(tc, R.mipmap.create)))));
                //fromResource(R.mipmap.emptyplaceresonator)
                cpt = cpt + 1;
            }
        }
    }

    //just a test
    public void onClickTest(View pView) {
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
    public void deleteLinkInGoogleMapAndHashMap(CLinkEntity pLink) {
        Log.d("test2", "hashmap => " + mMapPolylinesWithInteger);
        Polyline lPolylineToRemove = mMapPolylinesWithInteger.get(pLink.getId());
        Log.d("test2", "\n\n\n SALUT LE POLYLINE  : " + lPolylineToRemove);
        lPolylineToRemove.remove();
        //mMapPolylines.remove(pLink);
        mMapPolylinesWithInteger.remove(pLink.getId());
        if (pLink.getField() != null) {
            CFieldEntity lFieldToDelete = pLink.getField();
            List<CLinkEntity> lLinkFromField = pLink.getField().getLinks();
            for (CLinkEntity lL : lLinkFromField) {
                lL.setField(null);
                new CRestUpdate().updateLinkRest(lL);
            }
            deleteFieldInGoogleMapAndHashMap(lFieldToDelete);
        }
        //new CRestDelete().deleteLinkRest(pLink.getId());
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
    public void deleteFieldInGoogleMapAndHashMap(CFieldEntity pField) {
        Log.d("test2", " field ? " + mMapPolygonsWithInteger);
        if (mMapPolygonsWithInteger.containsKey(pField.getId())) {
            mMapPolygonsWithInteger.get(pField.getId()).remove();
            mMapPolygonsWithInteger.remove(pField);
            // TODO test
            Log.d("test3", "Field !? " + pField);

            Log.d("test3", "Field !? " + pField);
        } else {
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
    public void displayAllLinks() {
        Set<CLinkEntity> lSetLink = new HashSet<>();
        int lI = 0;
        for (CPortalEntity lPortal : mPortals) {
            for (lI = 0; lI < lPortal.getLinks().size(); lI++) {
                lSetLink.add(lPortal.getLinks().get(lI));
            }
        }
        for (CLinkEntity lLink : lSetLink){
            displayLink(lLink);
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
            displayField(lField);
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
    public void displayField(CFieldEntity pField) {

        if (pField != null) {
            List<CLinkEntity> lLinkArray = new ArrayList<>();

            Log.d("test", " field null ? -> " + pField.getLinks());

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

                mMapPolygonsWithInteger.put(pField.getId(), lPolygon);
            }
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
    public void displayLink(CLinkEntity pLink) {

        Polyline lLinkLine;
        List<CPortalEntity> lPortalLinkedArray = pLink.getPortals();

        LatLng[] lLatLngPortalLinkedArray = new LatLng[NB_PORTALS_LINK];
        if (lPortalLinkedArray.get(0).getTeam() != null) {
            int lTeamColor;
            if (lPortalLinkedArray.get(0).getTeam().getColor().equals("blue")) {
                lTeamColor = Color.BLUE;
            } else {
                lTeamColor = Color.RED;
            }
            for (int i = 0; i < NB_PORTALS_LINK; i++) {
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
    public void onCreateLink(View pView) {
        int lTeamColor;
        String lStringTeamColor;
        // Link color
        if (SCurrentPlayer.mPlayer.equals("blue")) {
            lTeamColor = Color.BLUE;
            lStringTeamColor = "BLUE";
        } else {
            lTeamColor = Color.RED;
            lStringTeamColor = "RED";
        }

        // just for the first key
        List<CKeyEntity> lPlayerKeysForPortal = SCurrentPlayer.mPlayer.getKeys();
        CKeyEntity lKey = lPlayerKeysForPortal.get(0);
        String lStringPortalTeamColor = "NONE";
        if (lPlayerKeysForPortal.get(0).getPortal().getTeam() != null) {
            lStringPortalTeamColor = lPlayerKeysForPortal.get(0).getPortal().getTeam().getColor();
        }
        // Log.d("test","team color -> " + lStringPortalTeamColor);

        if (lStringPortalTeamColor.equals(lStringTeamColor)) {
            LatLng lBeginningPortal = new LatLng(lKey.getPortal().getLat(), lKey.getPortal().getLong());
            LatLng lLastPortal = new LatLng(mTestPortal.getLat(), mTestPortal.getLong());
            Polyline lLinkToDraw = mMap.addPolyline(new PolylineOptions()
                    .add(lBeginningPortal, lLastPortal)
                    .width(LINE_WIDTH)
                    .color(lTeamColor));
        } else {
            Toast.makeText(getBaseContext(), LINK_PORTAL_NOT_GOOD_TEAM_FRENCH, Toast.LENGTH_SHORT).show();
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
    public void initDrawerAction(final CPortalEntity pPortal) {

        ImageButton lButtonAttack = generateButton(R.mipmap.attack);
        mLinear.addView(lButtonAttack);
        lButtonAttack.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                InitDrawerAttack();
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));

            }
        });

        if (SCurrentPlayer.mPlayer.getBombs().size() > 0) {
            ImageButton lButtonZone = generateButton(R.mipmap.zoneattackvalid);
            lButtonZone.setEnabled(true);
            mLinear.addView(lButtonZone);
            lButtonZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final CPortalEntity lPortal;
                    Log.d("test5", "dans le onclick !");
                    CClickPortalsAcitivity.useBombe(SCurrentPlayer.mPlayer.getBombs().get(0), mPortalClicked, SCurrentPlayer.mPlayer);
                /*for (CResonatorEntity lResonator : lPortal.getResonators()){
                    Log.d("test15", "ds la map de progressbar ? " + mProgressBars.get(lResonator.getId()));
                    mProgressBars.get(lResonator.getId()).setProgress(lResonator.getEnergy());
                    Log.d("test15", "progress apres degat! " + mProgressBars.get(lResonator.getId()).getProgress());

                }*/

                    //Log.d("test5", "rte" + pPortal.getResonators().get(0).getEnergy());
                }
            });
        } else {
            ImageButton lButtonZone = generateButton(R.mipmap.zoneattack);
            lButtonZone.setEnabled(false);
            mLinear.addView(lButtonZone);
        }

        ImageButton lButtonCreate = generateButton(R.mipmap.create);
        mLinear.addView(lButtonCreate);
        lButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                buildingDrawerExtend();
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));

            }
        });
        ImageButton lButtonBuild = generateButton(R.mipmap.build);
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
        ImageButton lButtonPirate = generateButton(R.mipmap.pirate);
        mLinear.addView(lButtonPirate);
        lButtonPirate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lPortalId = mPortalClicked.getId();
                int lPlayerId = SCurrentPlayer.mPlayer.getId();
                CHackPortal lHackPortal = new CHackPortal.CHackPortalBuilder().portal(lPortalId).player(lPlayerId).builder();
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.HACK_PORTAL.toString()).objectHackPortal(lHackPortal).build();
                CTyrusClient.sendMessage(lBeanToSend);

            }
        });
        ImageButton lButtonPirateKey = generateButton(R.mipmap.keyportal);
        mLinear.addView(lButtonPirateKey);
        lButtonPirateKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lPortalId = mPortalClicked.getId();
                int lPlayerId = SCurrentPlayer.mPlayer.getId();
                CHackPortalKey lHackPortalKey = new CHackPortalKey.CHackPortalKeyBuilder().portal(lPortalId).player(lPlayerId).builder();
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.HACK_PORTAL_KEY.toString()).objectHackPortalKey(lHackPortalKey).build();
                CTyrusClient.sendMessage(lBeanToSend);

            }
        });
        /*ImageButton lButtonCancel = generateButton(R.mipmap.cancel);
        mLinear.addView(lButtonCancel);*/
        buttonCanceled();
    }


    /**
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
    public Bitmap displayElementWithEnergy(IconGenerator pIg, int pId,int pHealth,int pEnergyMax) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        pIg.setBackground(null);
        Context lContext = getApplicationContext();
        LinearLayout lLinearLayout = new LinearLayout(lContext);
        lLinearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView lImage = new ImageView(lContext);
        LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(90, 90);
        lImage.setLayoutParams(lParams1);
        lImage.setImageDrawable(getResources().getDrawable(pId));
        lLinearLayout.addView(lImage);
        ProgressBar lHealth = new ProgressBar(lContext, null, android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams lParams2 = new LinearLayout.LayoutParams(100, 10);
        lHealth.setLayoutParams(lParams2);
        lHealth.setMax(pEnergyMax);
        lHealth.setProgress(pHealth);
        lHealth.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        //lHealth.setBackground(getDrawable(getResources().getColor(R.color.progbar)));
        //mProgressBars.put(pResonator.getId(), lHealth);
        lLinearLayout.addView(lHealth);
        pIg.setContentView(lLinearLayout);
        Bitmap bp = pIg.makeIcon();
// Resize the bitmap to 150x100 (width x height)
        //Bitmap bMapScaled = Bitmap.createScaledBitmap(bp, 50, 50, true);
        float metric = 25 *metrics.scaledDensity;
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bp, (int) metric, (int) metric, false);

// Loads the resized Bitmap into an ImageView
        return bMapScaled;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Bitmap displayEmptyPlace(IconGenerator pIg, int pId) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        pIg.setBackground(null);
        Context lContext = getApplicationContext();
        LinearLayout lLinearLayout = new LinearLayout(lContext);
        lLinearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView lImage = new ImageView(lContext);
        //lImage.setImageTintList(ColorStateList.valueOf(Color.WHITE));
        //lImage.setBackgroundColor(Color.BLACK);
        LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(100, 100);
        lImage.setLayoutParams(lParams1);
        lImage.setImageDrawable(getResources().getDrawable(pId));
        lLinearLayout.addView(lImage);
        pIg.setContentView(lLinearLayout);
        Bitmap bp = pIg.makeIcon();
        float metric = 25 *metrics.scaledDensity;
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bp, (int) metric, (int) metric, false);
        return bMapScaled;
    }


    public void displayPortal(IconGenerator pIg,LatLng pLatLng, CPortalEntity pPortal, int pId){
        pIg.setBackground(null);
        List<CResonatorEntity> lResonatorTeam1 = pPortal.getResonatorsTeamById(1);
        List<CResonatorEntity> lResonatorTeam2 = pPortal.getResonatorsTeamById(2);
        int lNbResonatorTeam1 = lResonatorTeam1.size();
        Log.d("test", Integer.toString(lNbResonatorTeam1));
        int lNbResonatorTeam2 = lResonatorTeam2.size();
        Log.d("test", Integer.toString(lNbResonatorTeam2));
        int lNbEmptyPlace = 8 - lNbResonatorTeam1 - lNbResonatorTeam2;
        Context context = getApplicationContext();
        LinearLayout info = new LinearLayout(context);
        info.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout info2 = possesionView(context, lNbResonatorTeam1, lNbResonatorTeam2, lNbEmptyPlace);
        /*new LinearLayout(context);
        info2.setOrientation(LinearLayout.VERTICAL);
        TextView snip1 = new TextView(context);
        snip1.setTextColor(Color.BLUE);
        snip1.setText(Integer.toString(lNbResonatorTeam1));
        info2.addView(snip1);
        TextView snip2 = new TextView(context);
        snip2.setTextColor(Color.RED);
        snip2.setText(Integer.toString(lNbResonatorTeam2));
        info2.addView(snip2);
        TextView snip3 = new TextView(context);
        snip3.setTextColor(Color.BLACK);
        snip3.setText(Integer.toString(lNbEmptyPlace));
        info2.addView(snip3);*/
        info.addView(info2);
        RelativeLayout info3 = new RelativeLayout(context);
        ImageView Im = new ImageView(context);
        Im.setImageDrawable(getResources().getDrawable(pId));
        info3.addView(Im);
        TextView lText = new TextView(new ContextThemeWrapper(context,R.style.iconPortal),null,0);
        lText.setText(Integer.toString(pPortal.getId()));
        lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        //lParams.
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        //lParams.addRule(RelativeLayout.);
        lText.setLayoutParams(lParams);
        info3.addView(lText);
        info.addView(info3);
        pIg.setContentView(info);
        Bitmap bp = pIg.makeIcon(Integer.toString(pPortal.getId()));
        Marker lMarker = mMap.addMarker(new MarkerOptions()
                .position(pLatLng).snippet("portal " + Integer.toString(pPortal.getId()))
                .icon(BitmapDescriptorFactory.fromBitmap(bp)));
        mPortalMarkers.add(lMarker);
        mPortalMarkersHashMap.put(pPortal.getId(), lMarker);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void reparationDrawerExtend(final CPortalEntity pPortal) {

        ImageButton lButtonCreate = generateButton(R.mipmap.keyportal);
        mLinear.addView(lButtonCreate);
        lButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                initDrawerLink();
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));

            }
        });
        ImageButton lButtonVirus = generateButton(R.mipmap.virus);
        mLinear.addView(lButtonVirus);
        lButtonVirus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lVirusId = SCurrentPlayer.mPlayer.getViruses().get(0).getId();
                int lPortalId = mPortalClicked.getId();
                int lPlayerId = SCurrentPlayer.mPlayer.getId();
                CPoseVirus lPoseVirus = new CPoseVirus.CPoseVirusBuilder().virusId(lVirusId).playerId(lPlayerId).portalId(lPortalId).build();
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_VIRUS.toString()).objectPoseVirus(lPoseVirus).build();
                CTyrusClient.sendMessage(lBeanToSend);
            }
        });
        ImageButton lButtonHeal = generateButton(R.mipmap.kitsoin);
        mLinear.addView(lButtonHeal);
        buttonCanceled();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void buildingDrawerExtend() {

        ImageButton lButtonChoiceResonator = generateButton(R.mipmap.resneutral);
        mLinear.addView(lButtonChoiceResonator);
        lButtonChoiceResonator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                InitDrawerResonator();
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));

            }
        });
        generateButtonExtend(R.mipmap.turret);
        generateButtonExtend(R.mipmap.multipir);
        generateButtonExtend(R.mipmap.lien);
        generateButtonExtend(R.mipmap.bouclier);
        buttonCanceled();
    }


    public void initDrawerLink(){
        //Log.d("test60", "-->" + mPlayer.getKeys());
        //Log.d("test60", "-" + mPlayer.getIdPortalsOfKeys());
        buttonCanceled();
        for (int i : SCurrentPlayer.mPlayer.getIdPortalsOfKeys()) {
            if (i != mPortalClicked.getId()) {
                Context context = getApplicationContext();
                RelativeLayout info = new RelativeLayout(context);
                final List<CKeyEntity> lKeys = SCurrentPlayer.mPlayer.getKeysByPortal(i);
                ImageButton lButton = generateButton(R.mipmap.keyportal);
                info.addView(lButton);
                lButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int lKeyId = lKeys.get(0).getId();
                        int lPortalId = mPortalClicked.getId();
                        int lPlayerId = SCurrentPlayer.mPlayer.getId();
                        CCreateLink lCreateLink = new CCreateLink.CCreateLinkBuilder().keyId(lKeyId).playerId(lPlayerId).portalId(lPortalId).build();
                        CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CREATE_LINK.toString()).objectCreateLink(lCreateLink).build();
                        CTyrusClient.sendMessage(lBeanToSend);
                    }
                });
                TextView lText = new TextView(context);
                lText.setText(Integer.toString(i));
                lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
                RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                //lParams.
                lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                //lParams.addRule(RelativeLayout.);
                lText.setLayoutParams(lParams);
                info.addView(lText);
                TextView lText2 = new TextView(context);
                lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getKeysByPortal(i).size()));
                lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
                info.addView(lText2);
                Log.d("test21", "---->" + i);
                mLinear.addView(info);
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageButton generateButton(int pIdMipMap) {
        ImageButton lButton = new ImageButton(this);
        Drawable mDrawable = getDrawable(pIdMipMap);
        lButton.setImageDrawable(mDrawable);
        return lButton;
    }


    public void InitDrawerResonator() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getLevelsOfResonators()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CResonatorEntity> lResonators = SCurrentPlayer.mPlayer.getResonatorsByLevel(i);
            ImageButton lButton = generateButton(R.mipmap.resneutral);
            info.addView(lButton);
            TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getResonatorsByLevel(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    Log.d("test_ws", "dans la methode");
                    CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().portalId(mPortalClicked.getId()).resonatorId(lResonators.get(0).getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);
                }
            });
        }

    }

    public void buttonCanceled() {
        ImageButton lButtonCancel = generateButton(R.mipmap.cancel);
        mLinear.addView(lButtonCancel);
        lButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);
            }
        });
    }

    /**
     * Remplace un portail dans la liste des portails par sa mise à jour.
     *
     * ----------
     *
     * Replaces a portal in the list by his updated version.
     */
    public void replacePortal(CPortalEntity pPortal) {
        int lIdToReplace = 0;
        for (int i = 0; i < mPortals.size(); i++) {
            if (mPortals.get(i).getId() == pPortal.getId()) {
                lIdToReplace = i;
                break;
            }
        }
        mPortals.set(lIdToReplace, pPortal);
        updatePortalIcon(pPortal);

    }

    public void updatePortalIcon(CPortalEntity pPortal) {
        Marker lMarker = mPortalMarkersHashMap.get(pPortal.getId());
        lMarker.remove();
        mResonatorMarkersHashMap.clear();
        LatLng test = new LatLng(pPortal.getLat(), pPortal.getLong());
        IconGenerator tc = new IconGenerator(this);
        tc.setTextAppearance(R.style.iconGenText);
        Toast.makeText(this, "Résonator Posed", Toast.LENGTH_SHORT);
        if (pPortal.getTeam() == null) {
            displayPortal(tc, test, pPortal, R.mipmap.portneutral);

        } else {
            if (pPortal.getTeam().getColor().equals("blue")) {
                displayPortal(tc, test, pPortal, R.mipmap.portblue);

            }
            if (pPortal.getTeam().getColor().equals("red")) {
                displayPortal(tc, test, pPortal, R.mipmap.portred);
            }
        }
    }

    public void createLink(CLinkEntity pLink){
        displayLink(pLink);
        if (pLink.getField() != null){
            displayField(pLink.getField());
        }
        for (CPortalEntity lPortal : pLink.getPortals()){
            for (int i = 0; i < mPortals.size(); i++){
                if (mPortals.get(i).getId() == lPortal.getId()){
                    mPortals.set(i, lPortal);
                }
            }
        }
    }

    public void applyVirus(CPortalEntity pPortal) {
        int lIdx = 0;
        for (int i = 0; i < mPortals.size(); i++) {
            if (mPortals.get(i).getId() == pPortal.getId()) {
                for (CLinkEntity lLink : mPortals.get(i).getLinks()) {
                    deleteLinkInGoogleMapAndHashMap(lLink);
                }
                lIdx = i;
                break;
            }
        }
        mPortals.set(lIdx, pPortal);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void InitDrawerAttack() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getRaritiesOfMunition()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CConsumableEntity> lMunitions = SCurrentPlayer.mPlayer.getMunitionsByRarity(i);
            ImageButton lButton = generateButton(R.mipmap.mun);
            //lButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            lButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.gold)));
            info.addView(lButton);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    Log.d("test_ws", "dans la methode");
                    //CAttackBuilding lAttack = new CAttackBuilding.CAttackBuildingBuilder().consumableId(lMunitions.get(0).getId())..build();
                    //CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    //Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    //CTyrusClient.sendMessage(lBean);
                    mMunition = lMunitions.get(0);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);
                    for (Marker lMarker : mResonatorMarkers) {
                        lMarker.remove();
                    }
                    mResonatorMarkers.clear();
                    displayResonators(mPortalClicked, SCurrentPlayer.mPlayer.getId());
                }
            });
            /*TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);*/
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getMunitionsByRarity(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
        }

    }

    /**
     *  acces au menu via un bouton flottant sur la carte
     *  -----
     *  access to Positron Menu with floating button on map
     * @param view
     */
    public void displayMenu(View view){
        Intent lMenuIntent = new Intent(this, CChoiceActivity.class);
        startActivity(lMenuIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiverWS);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        /**
         * Si l'ecran est oriente en mode paysage -> activation de la vue tactique
         * ------
         * Landscape screen -> tactical view
         */
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // university center
            Toast.makeText(getBaseContext(),getText(R.string.tactical_view),Toast.LENGTH_SHORT).show();
            LatLng lLoc = new LatLng(43.136478, 6.017839);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lLoc));
            mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 16));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        else {
            Toast.makeText(getBaseContext(),getText(R.string.portrait_view),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Supprime l'action du bouton de retour
     * -----
     * Delete back button action
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Toast.makeText(getApplicationContext(), getText(R.string.rm_back_button),
                    Toast.LENGTH_LONG).show();

        return false;
    }

    public LinearLayout possesionView(Context context, int pNbResonatorTeam1, int pNbResonatorTeam2, int pNbEmptyPlace){
        LinearLayout info2 = new LinearLayout(context);
        info2.setOrientation(LinearLayout.VERTICAL);
        TextView snip1 = new TextView(context);
        snip1.setTextColor(Color.BLUE);
        snip1.setText(Integer.toString(pNbResonatorTeam1));
        info2.addView(snip1);
        TextView snip2 = new TextView(context);
        snip2.setTextColor(Color.RED);
        snip2.setText(Integer.toString(pNbResonatorTeam2));
        info2.addView(snip2);
        TextView snip3 = new TextView(context);
        snip3.setTextColor(Color.BLACK);
        snip3.setText(Integer.toString(pNbEmptyPlace));
        info2.addView(snip3);
        return info2;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setHealth(){
        mHealthPlayer.setMax(SCurrentPlayer.mPlayer.getEnergyMax());
        mHealthPlayer.setProgress(SCurrentPlayer.mPlayer.getEnergy());
        mHealthPlayer.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setXp(){
        mXp.setMax(SCurrentPlayer.mPlayer.getEnergyMax());
        mXp.setProgress(SCurrentPlayer.mPlayer.getEnergy());
        mXp.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.xp)));
        Context context = getApplicationContext();
        TextView lNiv = (TextView) findViewById(R.id.niv);
        lNiv.setText("LVL " + Integer.toString(SCurrentPlayer.mPlayer.getLevel()));
        lNiv.setTextColor(ColorStateList.valueOf(Color.WHITE));
    }

    public List<Double> longitudeResonators(Double pLongitude,Double pEcart){
        List<Double> lLongitudes = new ArrayList<Double>();
        lLongitudes.add(pLongitude + pEcart);
        Double lEcart = pEcart/2;
        int i =1;
        while (i<7){
            lLongitudes.add(pLongitude +lEcart);
            lLongitudes.add(pLongitude +lEcart);
            lEcart=lEcart-pEcart/2;
            i=i+2;
        }
        lLongitudes.add(pLongitude + lEcart);
        return(lLongitudes);
    }

    public List<Double> latitudeResonators(double pLatitude,double pEcart){
        List<Double> lLatitudes = new ArrayList<Double>();
        lLatitudes.add(pLatitude);
        double lEcart = pEcart/2;
        for (int i=1;i<7;i++){
            if (i>2 && i<5){
                lEcart = pEcart;
                if (i%2==0){
                    lLatitudes.add(pLatitude+lEcart);
                }
                else{
                    lLatitudes.add(pLatitude-lEcart);
                }
            }
            else{
                lEcart = pEcart/2;
                if (i%2==0){
                    lLatitudes.add(pLatitude+lEcart);
                }
                else{
                    lLatitudes.add(pLatitude-lEcart);
                }
            }
        }
        lLatitudes.add(pLatitude);
        return(lLatitudes);
    }

    public List<Double> latitudeBuildings(double pLatitude,double pEcart) {
        List<Double> lLatitudes = new ArrayList<Double>();
        for (int i=0;i<4;i++){
            if (i%2==0){
                lLatitudes.add(pLatitude+pEcart);
            }
            else{
                lLatitudes.add(pLatitude-pEcart);
            }
        }
        return(lLatitudes);
    }

    public List<Double> longitudeBuildings(double pLongitude,double pEcart) {
        List<Double> lLongitudes = new ArrayList<Double>();
        int i=0;
        while (i<4){
            lLongitudes.add(pLongitude +pEcart);
            lLongitudes.add(pLongitude +pEcart);
            pEcart=-pEcart;
            i=i+2;
        }
        return(lLongitudes);
    }

    public int iconForBuilding(ABuildingEntity pBuilding){
        int i=0;
        return(i);
    }

    public int attributeIconToBuilding(ABuildingEntity pBuilding){
        int lIcon=0;
        //enlever le =0
        if (pBuilding instanceof CTurretEntity){
            //TODO mettre l'icone de base et non ceux de couleurs des tourelles
            //lIcon = R.mipmap.tourelle
        }
        if (pBuilding instanceof CMultiHackEntity){
            //TODO mettre l'icone de base et non ceux de couleurs
            lIcon = R.mipmap.multipir;
        }
        if (pBuilding instanceof CLinkImprovementEntity){
            //TODO mettre l'icone de base et non ceux de couleurs
            lIcon = R.mipmap.lien;
        }
        if (pBuilding instanceof CShieldEntity){
            //idée visuelle:établir un champ de couleur de team si présence autour du portail?
            //TODO mettre l'icone de base des boucliers
            //lIcon = R.mipmap.
        }
        return lIcon;
    }

    public void generateButtonExtend(final int pIcon){
        ImageButton lButtonChoice = generateButton(pIcon);
        mLinear.addView(lButtonChoice);
        lButtonChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinear.removeAllViews();
                if (pIcon==R.mipmap.multipir){
                    initDrawerMultiHack();
                }
                if (pIcon==R.mipmap.turret){
                    initDrawerTurret();
                }
                if (pIcon==R.mipmap.bouclier){
                    initDrawerShield();
                }
                if (pIcon==R.mipmap.lien){
                    initDrawerLinkImprovement();
                }
                mDrawerAction.openDrawer(mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,mScroll);
                //mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                mDrawerAction.setScrimColor(getResources().getColor(R.color.transparent));

            }
        });
    }

    public void initDrawerMultiHack() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getLevelsOfMultiHacks()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CMultiHackEntity> lMultiHacks = SCurrentPlayer.mPlayer.getMultiHacksByLevel(i);
            ImageButton lButton = generateButton(R.mipmap.multipir);
            info.addView(lButton);
            TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getMultiHacksByLevel(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    /*Log.d("test_ws", "dans la methode");
                    CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().portalId(mPortalClicked.getId()).resonatorId(lMultiHacks.get(0).getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);*/
                }
            });
        }

    }

    public void initDrawerTurret() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getLevelsOfTurrets()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CTurretEntity> lTurret= SCurrentPlayer.mPlayer.getTurretsByLevel(i);
            ImageButton lButton = generateButton(R.mipmap.turret);
            info.addView(lButton);
            TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getTurretsByLevel(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("test_ws", "dans la methode");
                    CPoseBuilding lPose = new CPoseBuilding.CPoseBuildingBuilder().portalId(mPortalClicked.getId()).buildingId(lTurret.get(0).getId()).player(SCurrentPlayer.mPlayer.getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_BUILDING.toString()).objectPoseBuilding(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    /*Log.d("test_ws", "dans la methode");
                    CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().portalId(mPortalClicked.getId()).resonatorId(lMultiHacks.get(0).getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);*/
                }
            });
        }

    }

    public void initDrawerShield() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getLevelsOfShields()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CShieldEntity> lShields = SCurrentPlayer.mPlayer.getShieldsByLevel(i);
            ImageButton lButton = generateButton(R.mipmap.bouclier);
            info.addView(lButton);
            TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getShieldsByLevel(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    /*Log.d("test_ws", "dans la methode");
                    CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().portalId(mPortalClicked.getId()).resonatorId(lMultiHacks.get(0).getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);*/
                }
            });
        }

    }

    public void initDrawerLinkImprovement() {
        buttonCanceled();
        //Log.d("test60", "objects -> " + mPlayer.getObjects());
        //Log.d("test60","-->"+mPlayer.getResonators());
        //Log.d("test60", "-" + mPlayer.getLevelsOfResonators());
        for (int i : SCurrentPlayer.mPlayer.getLevelsOfLinkImprovements()) {
            Context context = getApplicationContext();
            RelativeLayout info = new RelativeLayout(context);
            final List<CLinkImprovementEntity> lLinkImprovements = SCurrentPlayer.mPlayer.getLinkImprovementsByLevel(i);
            ImageButton lButton = generateButton(R.mipmap.lien);
            info.addView(lButton);
            TextView lText = new TextView(new ContextThemeWrapper(context, R.style.iconGenText), null, 0);
            lText.setText(Integer.toString(i));
            lText.setTextColor(ColorStateList.valueOf(Color.WHITE));
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lParams.
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            //lParams.addRule(RelativeLayout.);
            lText.setLayoutParams(lParams);
            info.addView(lText);
            TextView lText2 = new TextView(context);
            lText2.setText("x" + Integer.toString(SCurrentPlayer.mPlayer.getLinkImprovementsByLevel(i).size()));
            lText2.setTextColor(ColorStateList.valueOf(Color.BLACK));
            info.addView(lText2);
            Log.d("test21", "---->" + i);
            mLinear.addView(info);
            lButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPortalClicked = CActions.buildResonator(mPortalClicked, lResonators.get(0));
                    //CRestUpdate lUpdate = new CRestUpdate();
                    //SCurrentPlayer.mPlayer.removeObject((AObjectEntity) lResonators.get(0));
                    //lUpdate.updatePortalRest(mPortalClicked);
                    /*Log.d("test_ws", "dans la methode");
                    CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().portalId(mPortalClicked.getId()).resonatorId(lMultiHacks.get(0).getId()).build();
                    CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.POSE_RESONATOR.toString()).objectPoseResonator(lPose).build();
                    Log.d("test_ws", "bean null ? " + Boolean.toString(lBean == null));
                    CTyrusClient.sendMessage(lBean);
                    mDrawerAction.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, mScroll);*/
                }
            });
        }

    }
}



