package fr.univtln.m1dapm.groupec.tperron710.positron.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
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

import java.util.concurrent.ExecutionException;

import fr.univtln.m1dapm.groupec.tperron710.positron.CRUD.CRUDGet;
import fr.univtln.m1dapm.groupec.tperron710.positron.Entities.CPortal;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    public final static String GPS_OFF_FRENCH = "Le GPS est inactif!";
    public final static String GPS_ON_FRENCH = "Le GPS est actif!";
    public static final String PORTALS_URL = "http://localhost:9998/portals/";

    private GoogleMap mMap;
    private LocationManager locationManager;
    private CPortal portalMarker;
    private BitmapDescriptor bitmapDescriptor;
    private Circle circleMarker;
    private Circle userActionRadius;
    private LatLng latLng;
    private LatLng userLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                portalMarker = new CPortal(mMap, latLng, bitmapDescriptor);
            }
        });

        // ajout dun cercle lors  dun clic portail
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getBaseContext(),"test",Toast.LENGTH_SHORT).show();
                latLng = marker.getPosition();
                circleMarker = mMap.addCircle(new CircleOptions().center(latLng)
                        .radius(20).fillColor(Color.BLUE).strokeColor(Color.RED));
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
        String result = new CRUDGet().execute(url + String.valueOf(portalId)).get();
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
        userActionRadius = mMap.addCircle(new CircleOptions().center(userLatLng).radius(20).fillColor(Color.YELLOW));
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
}


