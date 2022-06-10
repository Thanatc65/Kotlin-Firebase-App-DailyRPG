package com.rpgtasks.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpgtasks.MainActivity
import com.rpgtasks.databinding.ActivityLogoutBinding

class LogoutActivity : AppCompatActivity() {

    private val binding by lazy{ActivityLogoutBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Intent(this@LogoutActivity, LoginActivity::class.java)
            .also { startActivity(it) }

    }
}