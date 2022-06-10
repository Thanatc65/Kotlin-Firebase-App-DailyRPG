package com.rpgtasks.advertise

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.MediaController
import android.widget.Toast
import com.rpgtasks.MainActivity
import com.rpgtasks.R
import com.rpgtasks.databinding.ActivityAdvertiseBinding
import com.rpgtasks.status.StatusFragment

class AdvertiseActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAdvertiseBinding.inflate(layoutInflater) }
    private var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        object : CountDownTimer(5000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.closeVideo.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.closeVideo.text = "X"
            }
        }.start()

        binding.closeVideo.setOnClickListener {
            Intent(this@AdvertiseActivity,MainActivity::class.java)
                .also { startActivity(it) }
        }

        if (mediaControls == null) {

            mediaControls = MediaController(this)
            mediaControls!!.setAnchorView(this.binding.videoView)
        }

        binding.videoView.setMediaController(mediaControls)
        binding.videoView.setVideoURI(
            Uri.parse("android.resource://"
                + packageName + "/" + R.raw.advertise_game))

        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
        }

        binding.videoView.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occured " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }

    }
}