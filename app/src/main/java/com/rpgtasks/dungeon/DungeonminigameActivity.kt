package com.rpgtasks.dungeon

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpgtasks.R
import com.rpgtasks.databinding.ActivityDungeonminigameBinding

class DungeonminigameActivity : AppCompatActivity() {

    private val binding by lazy{ActivityDungeonminigameBinding.inflate(layoutInflater)}
    var enemyHP = 100
    var maxHp = 100
    var floor = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.hpProgress.progressTintList = ColorStateList.valueOf(Color.RED)

        binding.enemyHp.text = enemyHP.toString()
        binding.hpProgress.progress = enemyHP
        binding.hpProgress.max = maxHp
        binding.maxHptext.text = maxHp.toString()
        binding.currentFloor.text = floor.toString()

        binding.screenEvent.setOnClickListener {

            binding.enemyHp.text = enemyHP.toString()
            binding.hpProgress.progress = enemyHP
            binding.hpProgress.max = maxHp
            binding.maxHptext.text = maxHp.toString()
            binding.currentFloor.text = floor.toString()
            enemyHP -= 5

            if(enemyHP <= 0){
                floor += 1
                binding.enemyHp.text = enemyHP.toString()
                binding.hpProgress.progress = enemyHP
                binding.hpProgress.max = maxHp

                if (floor == 2 ) {
                    enemyHP += 110
                    maxHp = 110
                    binding.hpProgress.max = maxHp
                    binding.monsterName.text = "Octoplus"
                    binding.monsterImage.setImageResource(R.drawable.monstertwo)

                }

                if (floor == 3 ) {

                    enemyHP += 200
                    maxHp = 200
                    binding.hpProgress.max = maxHp
                    binding.monsterName.text = "Memoos"
                    binding.monsterImage.setImageResource(R.drawable.monsterthree)

                }

                if (floor == 4 ) {

                    enemyHP += 300
                    maxHp = 300
                    binding.hpProgress.max = maxHp

                }
            }

        }

    }

}