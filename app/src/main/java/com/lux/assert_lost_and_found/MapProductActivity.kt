package com.lux.assert_lost_and_found

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lux.assert_lost_and_found.databinding.ActivityMapProductBinding

class MapProductActivity : AppCompatActivity() {

    val binding:ActivityMapProductBinding by lazy { ActivityMapProductBinding.inflate(layoutInflater) }
    val mapProductFragment:MapProductFragment  = MapProductFragment()
    val datas:Bundle by lazy { intent.extras?:Bundle() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_map_product)
        setContentView(binding.root)
        Toast.makeText(this, "${datas.getString("postTitle","postTitle")}", Toast.LENGTH_SHORT).show()

       supportFragmentManager.beginTransaction().add(R.id.frag_map_product,mapProductFragment).commit()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {

        }
    }
}