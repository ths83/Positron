package fr.univtln.groupc.activities.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.activities.portals.CClickPortalsAcitivity;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.entities_view.CTraceMapView;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.groupc.rest.CRestUpdate;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CMapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public final static float MAX_ZOOM_MAP = 18;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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
                    Log.d("test", "impossible de dézoomer");
                }
            }

        });
        // Location permission
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

        // Enable some gesture and parameters on map
        mMap = mapFragment.getMap();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 18));
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


        // Portals from database with REST
        final List<CPortalEntity> lPortals = new CRestGet().getPortalsRest();
        for (CPortalEntity p : lPortals) {
            //Log.d("test", " - > " + p.getLong() + " , " + p.getLat());
            //List<CResonatorEntity> lReso1 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),1);
            //List<CResonatorEntity> lReso2 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),2);
            /*if (p.getId()==7){
                Log.d("test",Integer.toString(lReso1.size()));
                Log.d("test",Integer.toString(lReso1.size()));
            }*/
            //Log.d("test", p.getResonators().toString());
            //Log.d("test", p.getResonators().toString());
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

        // Display links on start session -> avoid reductant latlng -> v2.0 to do
        int i = 0;
        for (CPortalEntity lP : lPortals) {
            for (i = 0; i < lP.getLinks().size(); i++) {
                new CTraceMapView().onDisplayLink(mMap, lP.getLinks().get(i));

            }
        }
        Log.d("map", String.valueOf(i));
        // Display fields on start session -> avoid reductant latlng -> v2.0 to do
        i = 0;
        for (CPortalEntity lP : lPortals) {
            for (i = 0; i < lP.getLinks().size(); i++) {
                if (lP.getLinks().get(i).getField() != null)
                    new CTraceMapView().onDisplayField(mMap, lP.getLinks().get(i).getField());
            }
        }
        Log.d("map", String.valueOf(i));
        // Location Service
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        /*// Portal created with user click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mbitmapDescriptor = BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_RED);
                //mPortalMarker = new CPortalEntity(mMap, mLatLng, mbitmapDescriptor);
                mTmpLink.add(latLng);
            }
        });*/

        // portal created with user click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mPosition == 1) {
                    mPosition = 0;
                }
                for (Marker lMarker : mResonatorMarkers){
                    lMarker.remove();
                }
                mResonatorMarkers.clear();
                //mBitmapDescriptor = BitmapDescriptorFactory.
                //     defaultMarker(BitmapDescriptorFactory.HUE_RED);
                //portalMarker = new CPortalEntity(mMap, latLng, bitmapDescriptor);
                //mTmpLink.add(latLng);
            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                CPortalEntity lPortal = new CRestGet().getPortalByIdRest(Integer.parseInt(marker.getTitle()));
                Log.d("test---->", marker.getTitle());
                List<CResonatorEntity> lResonatorTeam1 = lPortal.getResonatorsTeamById(1);
                List<CResonatorEntity> lResonatorTeam2 = lPortal.getResonatorsTeamById(2);
                int lNbResonatorTeam1 = lResonatorTeam1.size();
                Log.d("test team blue", Integer.toString(lNbResonatorTeam1));
                int lNbResonatorTeam2 = lResonatorTeam2.size();
                Log.d("test team red", Integer.toString(lNbResonatorTeam2));
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
                displayResonators(lPortal.getResonators(), lPortal);
                return info;
            }
        });


        // portal action radius when click on it

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (Marker lMarker : mResonatorMarkers){
                    lMarker.remove();
                }
                mResonatorMarkers.clear();
                LatLng lUserLatLng = marker.getPosition();
                if (mPosition == 0) {
                    mPosition = 1;
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(lUserLatLng));

                    //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.parseInt(marker.getTitle()));

                }
       /* // Portal action possibilities for players when click on it
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                View lView = new View(getBaseContext());
                onPortalClick(lView);
>>>>>>> 7c62d4d8e76410a831b8ecd57f4399366669696f*/
                return false;
            }
        });

        // New tests with objects
                /*for (CPortalEntity lPortal : lPortals){
                    Log.d("test", " - > " + "\nlat : " + lPortal.getLat() + "\nlong : " + lPortal.getLong());
                    mMarkerUserSelected = new LatLng(lPortal.getLat(),lPortal.getLong());
                    if (mMarkerUserSelected.equals(marker.getPosition())){
                        mClickPortalResonator = lPortal.getResonators();
                        mClickPortalObject = lPortal.getObjects();
                        mClickLink = lPortal.getLinks();
                        mResonatorString += mClickPortalObject.toString();
                        // resonators
                        for (CResonatorEntity lR : mClickPortalResonator){
                            mResonatorString += lR.toString();
                            Log.d("test", "-> " + lR.toString());
                        }
                        // objets
                        for (AObjectEntity lA : mClickPortalObject){

                            mObjectString += lA.toString();
                            Log.d("test", "- > " + lA.toString());

                        }
                        // links
                        for (CLinkEntity lL : mClickLink){
                            mLinkObjectString += lL.toString();
                            Log.d("test", "-> " + lL.toString());
                        }
                        Toast.makeText(getBaseContext(),/*mResonatorString + " " +*/ //mObjectString + mLinkObjectString,Toast.LENGTH_LONG).show();
        // Log.d("test", "-> " + /*mResonatorString + " " + */mObjectString + "link " + mLinkObjectString);

        //}

        //}

                /*// display tests for portals attr
                mLatLng = marker.getPosition();
                mLatLng2 = new LatLng(mLatLng.latitude + 0.0001, mLatLng.longitude + 0.0001);
                mCircleMarker = mMap.addCircle(new CircleOptions().center(mLatLng)
                        .radius(50).fillColor(Color.RED).strokeColor(Color.RED));
                marker.setTitle("Niveau: 2, Vie : 55%");

                mMarkerPortalAttr = mMap.addMarker(new MarkerOptions()
                        .position(mLatLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.multipirred)));

                mLatLng2 = new LatLng(mLatLng.latitude + 0.0, mLatLng.longitude + 0.0001);
                mMarkerPortalAttr = mMap.addMarker(new MarkerOptions()
                        .position(mLatLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.touramblue)));

                mLatLng2 = new LatLng(mLatLng.latitude + 0.0, mLatLng.longitude - 0.0001);
                mMarkerPortalAttr = mMap.addMarker(new MarkerOptions()
                        .position(mLatLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.touramblue)));


                mLatLng2 = new LatLng(mLatLng.latitude - 0.0001, mLatLng.longitude + 0.0001);
                mMarkerPortalAttr = mMap.addMarker(new MarkerOptions()
                        .position(mLatLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tourred)));

                mLatLng2 = new LatLng(mLatLng.latitude - 0.0001, mLatLng.longitude + 0.0);
                mMarkerPortalAttr = mMap.addMarker(new MarkerOptions()
                        .position(mLatLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.camred)));*/

                    /*return false;
        });*/


    }


    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            mUserLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (mPosition != 1) {
                //mMap.animateCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
            }
            //Log.i("test", "Latitude: "+String.valueOf(location.getLatitude())+" - Longitude: "+String.valueOf(location.getLongitude()));
            //mMap.moveCamera(CameraUpdateFactory.zoomTo(8f));
            if (mUserActionRadius != null) {
                mUserActionRadius.setCenter(mUserLatLng);
            } else {
                mUserActionRadius = mMap.addCircle(new CircleOptions().center(mUserLatLng).radius(50).fillColor(Color.YELLOW));
            }
        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    // user action radius when new position is detected
    @Override
    public void onLocationChanged(Location location) {
        /*mUserLatLng = new LatLng(location.getLatitude(),location.getLongitude());
        mUserActionRadius = mMap.addCircle(new CircleOptions().center(mUserLatLng).radius(50).strokeColor(Color.BLUE));
        Toast.makeText(getBaseContext(), mUserLatLng.toString(), Toast.LENGTH_SHORT).show();*/
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


    /**
     * some links tests between portals
     *
     * @param view
     */
    public void onLink(View view) {
        Object[] objects = mTmpLink.toArray();
        LatLng[] latLng1 = new LatLng[objects.length];

        for (int i = 0; i < objects.length; i++) {
            latLng1[i] = (LatLng) objects[i];
        }
        PolygonOptions polygonOptions = new PolygonOptions().add(latLng1).fillColor(Color.RED);
        mMap.addPolygon(polygonOptions);
    }

    /**
     * Portals actions for players
     */
    public void onPortalClick(View pView) {
        Intent lActionIntentPortal = new Intent(this, CClickPortalsAcitivity.class);
        startActivity(lActionIntentPortal);
    }

    /**
     * getter for map
     *
     * @return
     */
    public GoogleMap getmMap() {
        return mMap;
    }

    public void displayResonators(List<CResonatorEntity> pResonators, CPortalEntity pPortal) {
        double lLat = pPortal.getLat();
        double lLong = pPortal.getLong();
        //mMarkerUserSelected = new LatLng(lLat, lLong);
        //mCircleMarker = mMap.addCircle(new CircleOptions().center(mMarkerUserSelected).radius(pPortal.getRadius()).strokeColor(Color.BLACK));
        double lIdent = -0.0007;
        for (CResonatorEntity lResonator : pResonators) {
            LatLng test = new LatLng(lLat - 0.0004, lLong + lIdent);
            if (lResonator.getOwner().getTeam().getId() == 1) {

                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(test).title(Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.resblue))));
            } else {
                mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                        .position(test).title(Integer.toString(lResonator.getId()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.resred))));

            }
            lIdent = lIdent + 0.0002;
        }
        int i = pResonators.size();
        while (i < 8) {
            LatLng test = new LatLng(lLat - 0.0004, lLong + lIdent);
            /*
            mMap.addMarker(new MarkerOptions()
                    .position(test).title(Integer.toString(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.emptyplaceresonator)));*/
            mResonatorMarkers.add(mMap.addMarker(new MarkerOptions()
                    .position(test).title(Integer.toString(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.emptyplaceresonator))));
            lIdent = lIdent + 0.0002;
            i = i + 1;
        }

    }


}



