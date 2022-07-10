package com.lux.assert_lost_and_found

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lux.assert_lost_and_found.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.btnGet.setOnClickListener {
            val dialog:CustomDialog= CustomDialog(this)
            dialog.warningDialog()
        }
        binding.btnLost.setOnClickListener {
            val intent:Intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

    }
}