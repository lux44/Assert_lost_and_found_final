package com.lux.assert_lost_and_found

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import com.lux.assert_lost_and_found.databinding.ActivityWhatBinding
import javax.security.auth.callback.Callback
import kotlin.properties.Delegates

class WhatActivity : AppCompatActivity() {

    val binding:ActivityWhatBinding by lazy { ActivityWhatBinding.inflate(layoutInflater) }
    lateinit var getType1:String
    lateinit var getType2:String

    val getLat:Double by lazy { intent.getDoubleExtra("getLat",37.5609) }
    val getLng:Double by lazy { intent.getDoubleExtra("getLng",127.0347) }
    val getPlace:String by lazy { intent.getStringExtra("getPlace")?:"seoul"}
    val getRegion:String by lazy { intent.getStringExtra("getRegion")?:"구" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_what)
        setContentView(binding.root)

        Toast.makeText(this, "$getRegion", Toast.LENGTH_SHORT).show()

        

        binding.spinnerCategory1.adapter=ArrayAdapter.createFromResource(this,R.array.category1,R.layout.spinner_sample)
        (binding.spinnerCategory1.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)
         binding.spinnerCategory1.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val categories1 = resources.getStringArray(R.array.category1)
                getType1=categories1[p2]
                when(binding.spinnerCategory1.selectedItemPosition){
                    0->{
                        binding.spinnerCategory2.visibility=View.VISIBLE
                        binding.spinnerCategory2.adapter=ArrayAdapter.createFromResource(this@WhatActivity,R.array.category2_1,R.layout.spinner_sample)
                        (binding.spinnerCategory2.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)
                        
                        binding.spinnerCategory2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                val categories2_1 = resources.getStringArray(R.array.category2_1)
                                getType2=categories2_1[p2]
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                
                            }

                        }
                        
                    }
                    6->{
                        binding.spinnerCategory2.visibility=View.VISIBLE
                        binding.spinnerCategory2.adapter=ArrayAdapter.createFromResource(this@WhatActivity,R.array.category2_2,R.layout.spinner_sample)
                        (binding.spinnerCategory2.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)

                        
                    }
                    else ->{
                        binding.spinnerCategory2.visibility=View.INVISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                
            }

        }


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {
            val intent:Intent= Intent(this ,DetailActivity::class.java)
            intent.putExtra("getType1",getType1)
            intent.putExtra("getType2",getType2)
            intent.putExtra("getLat",getLat)
            intent.putExtra("getLng",getLng)
            intent.putExtra("getPlace",getPlace)
            intent.putExtra("getRegion",getRegion)
            startActivity(intent)
        }
    }
}