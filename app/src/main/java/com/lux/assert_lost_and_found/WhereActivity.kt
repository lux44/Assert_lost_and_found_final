package com.lux.assert_lost_and_found

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lux.assert_lost_and_found.databinding.ActivityWhereBinding
import com.lux.assert_lost_and_found.databinding.FragmentMapBinding

class WhereActivity : AppCompatActivity() {

    val binding:ActivityWhereBinding by lazy { ActivityWhereBinding.inflate(layoutInflater) }

    val mapFragment:MapFragment = MapFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.ivMap.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.frame_map,mapFragment).commit()

        }

    }
}