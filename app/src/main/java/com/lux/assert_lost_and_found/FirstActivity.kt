package com.lux.assert_lost_and_found

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lux.assert_lost_and_found.databinding.ActivityFirstBinding


class FirstActivity : AppCompatActivity() {

    val binding:ActivityFirstBinding by lazy { ActivityFirstBinding.inflate(LayoutInflater.from(this)) }
    val btn_join:Button by lazy { findViewById(R.id.btn_join) }
    val btn_login:Button by lazy { findViewById(R.id.btn_login) }
    val btn_arround:Button by lazy { findViewById(R.id.btn_around) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_intro)
        setContentView(binding.root)
        val permissions= arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (this?.checkSelfPermission(permissions[0])== PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions,100)
        }


        btn_arround.setOnClickListener {
            val builder:AlertDialog.Builder=AlertDialog.Builder(this)
            builder.setTitle("주의!")
            builder.setMessage("둘러보기는 분실물 목록만 확인이 가능해요! 둘러보시겠어요?")

            builder.setPositiveButton("yes"
            ) { p0, p1 ->
                val intent=Intent(this,SecondActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("No",null)
            builder.create().show()

        }
        btn_login.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }



    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==100&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "위치 사용 허가", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "위치 사용 불가", Toast.LENGTH_SHORT).show()
        }
    }
}