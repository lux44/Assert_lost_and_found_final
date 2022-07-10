package com.lux.assert_lost_and_found

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import com.lux.assert_lost_and_found.databinding.WarningDialogBinding

class CustomDialog (val context: Context) {

    private val dialog:Dialog= Dialog(context)

    val binding by lazy {WarningDialogBinding.inflate(LayoutInflater.from(context))  }
    fun warningDialog(){
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()


        binding.btnWarning1.setOnClickListener {
            val intent:Intent= Intent(context,WhereActivity::class.java)
            context.startActivity(intent)
        }

    }




}