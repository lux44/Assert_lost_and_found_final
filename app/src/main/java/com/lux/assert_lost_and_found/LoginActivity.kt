package com.lux.assert_lost_and_found

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lux.assert_lost_and_found.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val btn_google by lazy { binding.btnLoginGoogle }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)

        btn_google.setOnClickListener {
            val intent:Intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}