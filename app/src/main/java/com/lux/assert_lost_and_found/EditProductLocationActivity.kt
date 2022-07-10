package com.lux.assert_lost_and_found

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import com.lux.assert_lost_and_found.databinding.ActivityEditProductLocationBinding
import java.lang.StringBuilder
import java.util.*

class EditProductLocationActivity : AppCompatActivity() {
    val binding:ActivityEditProductLocationBinding by lazy { ActivityEditProductLocationBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_edit_product_location)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.btnNext.setOnClickListener {
            val address:String=binding.etShopAddress.text.toString()
            val geocoder:Geocoder= Geocoder(this, Locale.KOREA)
            
            val list:MutableList<Address> = geocoder.getFromLocationName(address,3)
            
            val lat:Double=list[0].latitude
            val lng:Double=list[0].longitude
            
            val latLng:LatLng= LatLng(lat,lng)
            //Toast.makeText(this, "$latLng", Toast.LENGTH_SHORT).show()

            val intent:Intent=Intent(this,DetailExplainActivity::class.java)
            startActivity(intent)
        }








    }
}