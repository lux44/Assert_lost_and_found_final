package com.lux.assert_lost_and_found

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.core.content.contentValuesOf
import com.lux.assert_lost_and_found.databinding.DialogRegisterBinding

class RegisterDialog (val context: Context) {
    val binding:DialogRegisterBinding by lazy { DialogRegisterBinding.inflate(LayoutInflater.from(context)) }
    private val dialog:Dialog= Dialog(context)

    fun registerDialog(){
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        binding.btnMain.setOnClickListener {
            val intent=Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}