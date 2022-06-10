package com.rpgtasks.dungeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.rpgtasks.databinding.ActivityDungeonBinding

class DungeonActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDungeonBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.enterDungeonbutton.setOnClickListener {
//            val builder = AlertDialog.Builder(binding.root.context)
//            builder.setMessage("Not Open Yet")
//            val alert = builder.create()
//            alert.show()
            Intent(this@DungeonActivity,DungeonminigameActivity::class.java)
                .also { startActivity(it) }
        }

    }
}