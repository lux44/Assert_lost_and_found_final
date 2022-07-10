package com.lux.assert_lost_and_found

import android.app.AlertDialog
import android.content.ClipData
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
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
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lux.assert_lost_and_found.databinding.FragmentMapBinding
import retrofit2.*
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.HashMap
import kotlin.math.ln

class MapFragment : Fragment() {

    val binding: FragmentMapBinding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    val providerClient: FusedLocationProviderClient by lazy { FusedLocationProviderClient(context as WhereActivity) }
    var myLocation: LatLng? = null
    var gMap: GoogleMap? = null

    lateinit var getRegion:String
    val codeValue:Array<String> by lazy { resources.getStringArray(R.array.code_value) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager: FragmentManager = childFragmentManager
        val mapFragment: SupportMapFragment =
            fragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment

        val locationRequest = com.google.android.gms.location.LocationRequest.create()
        locationRequest.priority =
            com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                val lat: Double = p0.lastLocation.latitude
                val lng: Double = p0.lastLocation.longitude
                myLocation = LatLng(lat, lng)
                gMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation!!, 16F))
                Toast.makeText(context, "$lat", Toast.LENGTH_SHORT).show()
            }
        }
        if (ActivityCompat.checkSelfPermission(
                context as WhereActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context as WhereActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        providerClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )


        mapFragment.getMapAsync { gMap ->

            this@MapFragment.gMap = gMap

            val seoul: LatLng = LatLng(37.5609, 127.0347)
            if (myLocation != null) gMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    myLocation!!,
                    16F
                )
            )
            else gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 16F))


            val uiSetting: UiSettings = gMap.uiSettings
            uiSetting.isZoomGesturesEnabled = true
            uiSetting.isScrollGesturesEnabled = true
            uiSetting.isMyLocationButtonEnabled = true

            uiSetting.isZoomControlsEnabled = true
            if (ActivityCompat.checkSelfPermission(
                    context as WhereActivity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    context as WhereActivity,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@getMapAsync
            }

            gMap.isMyLocationEnabled = true


            gMap.setOnMapClickListener {
                val lat: Double = it.latitude
                val lng: Double = it.longitude

                val markerOption: MarkerOptions = MarkerOptions()
                markerOption.position(it)
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                gMap.addMarker(markerOption)

                val geocoder: Geocoder = Geocoder(context, Locale.KOREA)
                val addressList: MutableList<Address> = geocoder.getFromLocation(lat, lng, 3)
                val address: String = addressList[0].getAddressLine(0)
                Toast.makeText(context, "$address", Toast.LENGTH_SHORT).show()

                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setTitle("이 주소로 선택 하시겠습니까?")
                builder.setMessage("$address")
                builder.setPositiveButton(
                    "예",
                    DialogInterface.OnClickListener { dialogInterface, i ->

//                        for (i in 0 until codeValue.size){
//                            if (address.contains(codeValue[i])){
//                                getRegion=codeValue[i]
//                            }
//                        }
                        for(value in codeValue){
                            if(address.contains(value)){
                                getRegion=value
                            }
                        }

                        val intent: Intent = Intent(context, WhatActivity::class.java)
                        intent.putExtra("getLat",lat)
                        intent.putExtra("getLng", lng)
                        intent.putExtra("getPlace", address)
                        intent.putExtra("getRegion",getRegion)

                        startActivity(intent)
                    })
                builder.setNegativeButton("다시 선택", null)
                builder.create().show()


            }


        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}