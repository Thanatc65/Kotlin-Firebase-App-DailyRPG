package com.rpgtasks.dungeon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpgtasks.databinding.ActivityDungeonEventBinding

class DungeonEventActivity : AppCompatActivity() {

    val binding by lazy { ActivityDungeonEventBinding.inflate(layoutInflater) }

    val plot = PlotStory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.path1.setOnClickListener {

            plot.select(plot.nextpath1)
        }

        binding.path2.setOnClickListener {

            plot.select(plot.nextpath2)
        }

        binding.path3.setOnClickListener {

            plot.select(plot.nextpath3)
        }

        binding.path4.setOnClickListener {

            plot.select(plot.nextpath4)
        }

        plot.start()

    }
}