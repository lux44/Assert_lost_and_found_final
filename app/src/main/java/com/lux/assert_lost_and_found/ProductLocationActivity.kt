package com.lux.assert_lost_and_found

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lux.assert_lost_and_found.databinding.ActivityProductLocationBinding

class ProductLocationActivity : AppCompatActivity() {
    val binding:ActivityProductLocationBinding by lazy { ActivityProductLocationBinding.inflate(layoutInflater) }

    val bundle:Bundle? by lazy { intent.extras }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_product_location)
        setContentView(binding.root)
        Toast.makeText(this, "${bundle?.getString("postTitle","postTitle")}", Toast.LENGTH_SHORT).show()

        binding.btnEditLocation.setOnClickListener {
            val intent:Intent= Intent(this,MapProductActivity::class.java)
            intent.putExtras(bundle!!)
            val dialog:ProductLocationDialog= ProductLocationDialog(this)
            dialog.plDialog(intent)
        }
        binding.btnMine.setOnClickListener {

        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {

        }
    }
    fun deliveryDatas(intent: Intent) {
        startActivity(intent)
    }
}