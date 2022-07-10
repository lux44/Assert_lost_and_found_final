package com.lux.assert_lost_and_found

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lux.assert_lost_and_found.databinding.FragmentMap2Binding
import java.lang.StringBuilder
import java.util.*

class MapProductFragment : Fragment() {

    val binding:FragmentMap2Binding by lazy { FragmentMap2Binding.inflate(layoutInflater) }
    val providerClient: FusedLocationProviderClient by lazy { FusedLocationProviderClient(context as MapProductActivity) }
    var myLocation:LatLng?=null
    var gMap: GoogleMap?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager:FragmentManager=childFragmentManager
        val map2Fragment:SupportMapFragment=fragmentManager.findFragmentById(R.id.fragment_map2) as SupportMapFragment

        val locationRequest:LocationRequest= LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val locationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                val lat:Double=p0.lastLocation.latitude
                val lng:Double=p0.lastLocation.longitude
                myLocation= LatLng(lat,lng)
                gMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation!!,16F))


            }
        }
        if (ActivityCompat.checkSelfPermission(
                context as MapProductActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context as MapProductActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        providerClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())

        map2Fragment.getMapAsync {  gMap->
            this.gMap=gMap

            val seoul:LatLng= LatLng(37.5609, 127.0347)
            if (myLocation!=null) gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation!!,16F))
            else gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,16F))

            val uiSettings:UiSettings=gMap.uiSettings
            uiSettings.isZoomGesturesEnabled=true
            uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true
            uiSettings.isScrollGesturesEnabled=true

            if (ActivityCompat.checkSelfPermission(context as MapProductActivity,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context as MapProductActivity,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            {
                return@getMapAsync
            }

            gMap.isMyLocationEnabled=true

            gMap.setOnMapClickListener {
                val lat:Double=it.latitude
                val lng:Double=it.longitude

                val markerOptions:MarkerOptions= MarkerOptions()
                markerOptions.position(it)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                gMap.addMarker(markerOptions)

                val geocoder:Geocoder= Geocoder(context, Locale.KOREA)
                val addressList:MutableList<Address> = geocoder.getFromLocation(lat,lng,3)
                val address:String=addressList[0].getAddressLine(0)

                val builder:AlertDialog.Builder=AlertDialog.Builder(context).setTitle("이 주소로 선택하시겠습니까?")
                    .setMessage("$address")
                builder.setPositiveButton("예", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent:Intent= Intent(context,DetailExplainActivity::class.java)
                    (context as MapProductActivity).datas.putDouble("productLat",lat)
                    (context as MapProductActivity).datas.putDouble("productLng",lng)
                    intent.putExtras((context as MapProductActivity).datas)
                    startActivity(intent)
                })
                builder.setNegativeButton("다시 선택",null)
                builder.create().show()
            }

        }

    }
}