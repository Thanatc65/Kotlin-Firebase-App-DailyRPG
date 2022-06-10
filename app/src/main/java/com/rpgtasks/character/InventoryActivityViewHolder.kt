package com.rpgtasks.character

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rpgtasks.FakeAuth
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.databinding.ItemInventoryBinding

class InventoryActivityViewHolder(private val binding : ItemInventoryBinding)
    : RecyclerView.ViewHolder(binding.root) {

    private val db = FirebaseFirestore.getInstance()
    var equips = "Equip"
    var currentequip = "Equip"

        fun bind(inventory: Inventory){
            binding.equipImage.setImageResource(inventory.imageequipment)
            binding.equipmentName.text = inventory.equipmentname
            binding.sourceImage.text = inventory.sourceImage
            binding.sourceImage.visibility = View.INVISIBLE

            binding.equipBotton.setOnClickListener {

                if(currentequip == equips){
                    binding.equipBotton.text = "Unequip"
                    currentequip += 1

                    val equip = Equipment(
                        binding.sourceImage.text.toString()
                    )
                    db.collection("userId")
                        .document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .collection(FirestoreSchema.CHARACTER)
                        .document(FakeAuth.currentUser.id)
                        .set(equip, SetOptions.merge())

                }else{
                    binding.equipBotton.text = "Equip"
                    currentequip = "Equip"

                    db.collection("userId")
                        .document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .collection(FirestoreSchema.CHARACTER)
                        .document(FakeAuth.currentUser.id)
                        .delete()

                }

            }

        }
}