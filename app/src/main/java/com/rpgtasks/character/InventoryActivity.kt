package com.rpgtasks.character

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rpgtasks.FakeDb
import com.rpgtasks.databinding.ActivityInventoryBinding

class InventoryActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInventoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.inventoryLists.apply {
            adapter = InventoryActivityAdapter(FakeDb.inventorydata)
            setHasFixedSize(true)
        }


    }
}