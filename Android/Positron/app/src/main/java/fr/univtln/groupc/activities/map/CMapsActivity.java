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

import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.entities_view.CTraceMapView;
import fr.univtln.groupc.rest.CRestGet;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CMapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public final static float MAX_ZOOM_MAP = 17f;

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private CPortalEntity mPortalMarker;
    private BitmapDescriptor mBitmapDescriptor;
    private Circle mCircleMarker;
    private Circle mUserActionRadius;
    private LatLng mLatLng;
    private LatLng mUserLatLng;
    private List<LatLng> mTmpLink = new ArrayList<>();

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
        Button buttonZoomOut= (Button) findViewById(R.id.bzoomout);
        Button buttonZoomIn= (Button) findViewById(R.id.bzoomin);
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMap.getCameraPosition().zoom >= 17) {
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
                if (mMap.getCameraPosition().zoom < 18.5) {
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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 17.5));
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


        // Portals from database with REST
        final List<CPortalEntity> lPortals = new CRestGet().getPortalsRest();
        for (CPortalEntity p : lPortals) {
            Log.d("test", " - > " + p.getLong() + " , " + p.getLat());
            List<CResonatorEntity> lReso1 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),1);
            List<CResonatorEntity> lReso2 = new CRestGet().getResonatorsByPortalAndTeamRest(p.getId(),2);
            //p.attributeTeam(lReso1,lReso2);
            LatLng test = new LatLng(p.getLat(), p.getLong());
            IconGenerator tc =new IconGenerator(this);
            tc.setTextAppearance(R.style.iconGenText);
            if (p.getTeam() == null) {
                tc.setBackground(getResources().getDrawable(R.mipmap.portneutral));
                Bitmap bp=tc.makeIcon(Integer.toString(p.getId()));
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
                    Bitmap bp=tc.makeIcon(Integer.toString(p.getId()));
                    mMap.addMarker(new MarkerOptions()
                            .position(test).title(Integer.toString(p.getId())).icon(BitmapDescriptorFactory.fromBitmap(bp)));
                }
                if (p.getTeam().getColor().equals("red")) {
                    tc.setBackground(getResources().getDrawable(R.mipmap.portred));
                    //tc.setTextAppearance(R.style.iconGenText);
                    Bitmap bp=tc.makeIcon(Integer.toString(p.getId()));
                    mMap.addMarker(new MarkerOptions()
                            .position(test).title(Integer.toString(p.getId()))
                            .icon(BitmapDescriptorFactory.fromBitmap(bp)));
                }
            }
        }

        // Display links on start session -> avoid reductant latlng -> v2.0 to do
        int i = 0;
        for (CPortalEntity lP : lPortals){
            for (i = 0 ; i < lP.getLinks().size(); i++){
                new CTraceMapView().onDisplayLink(mMap,lP.getLinks().get(i));

            }
        }
        Log.d("map", String.valueOf(i));
        // Display fields on start session -> avoid reductant latlng -> v2.0 to do
        i = 0;
        for (CPortalEntity lP : lPortals){
            for (i = 0 ; i < lP.getLinks().size(); i++){
                new CTraceMapView().onDisplayField(mMap,lP.getLinks().get(i).getField());
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
                mBitmapDescriptor = BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_RED);
                //portalMarker = new CPortalEntity(mMap, latLng, bitmapDescriptor);
                mTmpLink.add(latLng);
            }
        });

        // portal action radius when click on it
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                //List<CResonatorEntity> lResonators = new CRestGet().getResonatorsByPortalRest(Integer.getInteger(marker.getTitle()));
                //for (CResonatorEntity resonator : lResonators) {
                  //  if (resonator.getOwner().getTeam().equals("red")){

                    //}

                //}
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
            Log.i("test", "Latitude: "+String.valueOf(location.getLatitude())+" - Longitude: "+String.valueOf(location.getLongitude()));
            mUserLatLng=new LatLng(location.getLatitude(),location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
            //mMap.moveCamera(CameraUpdateFactory.zoomTo(8f));
            if (mUserActionRadius!=null){
                mUserActionRadius.setCenter(mUserLatLng);
            }
            else {
                mUserActionRadius = mMap.addCircle(new CircleOptions().center(mUserLatLng).radius(50).fillColor(Color.YELLOW));
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLng(mUserLatLng));
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
        Toast.makeText(getBaseContext(),GPS_ON_FRENCH,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        // player needs to active gps sensor
        Toast.makeText(getBaseContext(),GPS_OFF_FRENCH,Toast.LENGTH_SHORT).show();
        // if gps is disabled gps settings will be opened to enable it
        Intent enableGpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(enableGpsIntent);
    }


    /**
     * some links tests between portals
     * @param view
     */
    public void onLink(View view){
        Object[] objects = mTmpLink.toArray();
        LatLng[] latLng1 = new LatLng[objects.length];

        for (int i = 0 ; i < objects.length;i++){
            latLng1[i] = (LatLng) objects[i];
        }
        PolygonOptions polygonOptions = new PolygonOptions().add(latLng1).fillColor(Color.RED);
        mMap.addPolygon(polygonOptions);
    }

    /**
     * getter for map
     * @return
     */
    public GoogleMap getmMap() {
        return mMap;
    }
}


