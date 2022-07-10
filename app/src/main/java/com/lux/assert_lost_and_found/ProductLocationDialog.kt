package com.lux.assert_lost_and_found

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.WindowManager
import com.lux.assert_lost_and_found.databinding.DialogProductLocationBinding

class ProductLocationDialog (val context: Context) {
    val binding:DialogProductLocationBinding by lazy { DialogProductLocationBinding.inflate(
        LayoutInflater.from(context)) }

    private val dialog:Dialog= Dialog(context)

    fun plDialog(intent: Intent) {
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        binding.btn1.setOnClickListener {
            (context as ProductLocationActivity).deliveryDatas(intent)
        }
        binding.btn2.setOnClickListener {
            val intent:Intent= Intent(context,EditProductLocationActivity::class.java)
            context.startActivity(intent)
        }
    }
}