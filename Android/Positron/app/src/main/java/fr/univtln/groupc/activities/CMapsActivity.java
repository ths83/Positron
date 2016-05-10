package fr.univtln.groupc.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
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


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.activities.portals.CAttackPortalsView;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.rest.CCrudGet;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CMapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    public final static String apiURL = "http://localhost:9998";

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public static final String PORTALS_URL = "http://localhost:9998/portals/";

    private GoogleMap mMap;
    private LocationManager locationManager;
    private CPortalEntity portalMarker;
    private BitmapDescriptor bitmapDescriptor;
    private Circle circleMarker;
    private Circle userActionRadius;
    private LatLng latLng;
    private LatLng userLatLng;
    private List<LatLng> tmpLink = new ArrayList<>();

    private Marker melbourne;
    private LatLng latLng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar =   (Toolbar) findViewById(R.id.toolbar);
        final Intent actionPortalIntent = new Intent(this, CAttackPortalsView.class);
        //List<CPortalEntity> cPortalEntities = getPortalsRest();
        //Log.d("t", cPortalEntities.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f));

        // Location Service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        // creation dun portail lors dun clic sur lecran
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                bitmapDescriptor = BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_RED);
                //portalMarker = new CPortalEntity(mMap, latLng, bitmapDescriptor);
                tmpLink.add(latLng);
            }
        });

        // ajout dun cercle lors  dun clic portail
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                // il s'agit uniquement de tsts d'affichage, je m'excuse par avance de la saleté du code
                latLng = marker.getPosition();
                latLng2 = new LatLng(latLng.latitude + 0.0001, latLng.longitude + 0.0001);
                circleMarker = mMap.addCircle(new CircleOptions().center(latLng)
                        .radius(50).fillColor(Color.RED).strokeColor(Color.RED));
                marker.setTitle("Niveau: 2, Vie : 55%");

                melbourne = mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.multipirred)));

                latLng2 = new LatLng(latLng.latitude + 0.0, latLng.longitude + 0.0001);
                melbourne = mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.touramblue)));

                latLng2 = new LatLng(latLng.latitude + 0.0, latLng.longitude - 0.0001);
                melbourne = mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.touramblue)));


                latLng2 = new LatLng(latLng.latitude - 0.0001, latLng.longitude + 0.0001);
                melbourne = mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.tourred)));

                latLng2 = new LatLng(latLng.latitude - 0.0001, latLng.longitude + 0.0);
                melbourne = mMap.addMarker(new MarkerOptions()
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.camred)));

                startActivity(actionPortalIntent);

                return false;
            }
        });

        // init portal from database, ca fonctionne pas pour l'instant
        try {
            getPortal(PORTALS_URL,1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // methode pour recuperer un portail et afficher le string en toast lors du lancement de l'activite
    public String getPortal(String url,int portalId) throws ExecutionException,InterruptedException{
        String result = new CCrudGet().execute(url + String.valueOf(portalId)).get();
        Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
        return result;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    // cercle pour l'utilisateur, initialisation lors du changement de position de celui-ci
    @Override
    public void onLocationChanged(Location location) {
        userLatLng = new LatLng(location.getLatitude(),location.getLongitude());
        userActionRadius = mMap.addCircle(new CircleOptions().center(userLatLng).radius(50).fillColor(Color.YELLOW));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(),GPS_ON_FRENCH,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(),GPS_OFF_FRENCH,Toast.LENGTH_SHORT).show();
        Intent enableGpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(enableGpsIntent);
    }

    public void onLink(View view){
        Object[] objects = tmpLink.toArray();
        LatLng[] latLng1 = new LatLng[objects.length];

        for (int i = 0 ; i < objects.length;i++){
            latLng1[i] = (LatLng) objects[i];
        }
        PolygonOptions polylineOptions = new PolygonOptions().add(latLng1).fillColor(Color.RED);
        mMap.addPolygon(polylineOptions);
    }

    public List<CPortalEntity> getPortalsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = apiURL + "/portals";
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = null;
        try {
            lPortalsJson = new RestGet().execute(lUrlString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            lPortals = lMapper.readValue(lPortalsJson, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lPortals;
    }

    private class RestGet extends AsyncTask<String, String, String> {
        public RestGet() {
            super();
        }
        /*
         * Permet de faire les GET de rest.
         * On se connecte à l'URL et on recupere le json.
         */

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String resultToDisplay = "";
            InputStream in = null;
            String json = "";

            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                in.close();
                json = sb.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}


