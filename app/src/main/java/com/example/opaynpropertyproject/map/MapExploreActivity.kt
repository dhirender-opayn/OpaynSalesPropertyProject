package com.example.opaynpropertyproject.map

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opaynpropertyproject.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapExploreActivity : AppCompatActivity(),OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    internal lateinit var mLastLocation: Location
    internal lateinit var mLocationResult: LocationRequest
    internal lateinit var mLocationCallback: LocationCallback
    internal var mCurrLocationMarker: Marker? = null
    internal lateinit var mLocationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_explore)
        overridePendingTransition(0,0)

        supportActionBar!!.hide()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        // Add a marker in India and move the camera
        val myLocation = LatLng(30.895340, 75.835750)
        mMap?.addMarker(MarkerOptions().position(myLocation).title("Marker in India"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        val zoomLevel = 16.0f //This goes up to 21
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoomLevel))
    }
}