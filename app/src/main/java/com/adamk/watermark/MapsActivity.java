package com.adamk.watermark;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private boolean tipMode = false;
    private LinearLayout linearLayout;
    private PopupWindow popupWindow;
    private FloatingActionButton test;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        linearLayout = new LinearLayout(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        t = new ActionBarDrawerToggle(this,drawer,R.string.open, R.string.close);

        drawer.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nav_view_google);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){

                    case R.id.reportMenuItem:{
                        tipMode = true;
                        Toast.makeText(MapsActivity.this,"press the fucking map",Toast.LENGTH_LONG).show();
                        drawer.closeDrawers();
                    }

                }
                return false;
            }
        });




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onMapClick(LatLng latLng) {
        if (tipMode) {
            addTestPopup(latLng);
        }
        double lat = latLng.latitude;
        double lng = latLng.longitude;

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }


    public void addTestPopup(final LatLng location) {
        tipMode = false;
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.addtestpopup, null);
        Button submitBtnAddBar = (Button) popupView.findViewById(R.id.submitBtn);
        final TextInputEditText phValue = (TextInputEditText) popupView.findViewById(R.id.barName);
        TextInputEditText oxygenLevel = (TextInputEditText) popupView.findViewById(R.id.beerName);
        TextInputEditText turbidityLevel = (TextInputEditText) popupView.findViewById(R.id.beerPrice);
        submitBtnAddBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phValue.getText().toString().equals("8")) {
                    mMap.addMarker(new MarkerOptions().position(location).title("save Water").icon(BitmapDescriptorFactory.fromResource(R.drawable.water)));
                    popupWindow.dismiss();
                }
                else{
                    mMap.addMarker(new MarkerOptions().position(location).title("bajs water"));
                }
            }
        });


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

    }
}
