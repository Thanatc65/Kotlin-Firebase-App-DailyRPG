package com.rpgtasks

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.rpgtasks.databinding.ActivitySplashScreenBinding
import com.rpgtasks.login.LoginActivity
import java.util.concurrent.CountedCompleter

class SplashScreen : AppCompatActivity() {

    private val binding by lazy{ActivitySplashScreenBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logoSplash.alpha = 0f
        binding.logoSplash.animate().setDuration(1400).alpha(1f).withEndAction{
            val end = Intent(this,LoginActivity::class.java)
            startActivity(end)
            finish()
        }

        binding.splashProgress.max = 100
        val currrentProgress = 100

        ObjectAnimator.ofInt(binding.splashProgress,"progress",currrentProgress)
            .setDuration(1400)
            .start()

    }
}