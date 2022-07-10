package com.lux.assert_lost_and_found

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lux.assert_lost_and_found.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {
    val binding:ActivityItemBinding= ActivityItemBinding.inflate(layoutInflater)
    val button by lazy { binding.btnReceived}
    val tv_category by lazy { binding.tvCategoryDetail }
    val tv_title by lazy { binding.tvTitleDetail }
    val iv1 by lazy { binding.iv1 }
    val iv2 by lazy { binding.iv2 }
    val tv_time by lazy { binding.tvTitleDetail }
    val tv_f_loca by lazy { binding.tvFoundLocation }
    val tv_p_loca by lazy { binding.tvProductLocation }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_item)
        setContentView(binding.root)




        button.setOnClickListener {

        }
    }
}