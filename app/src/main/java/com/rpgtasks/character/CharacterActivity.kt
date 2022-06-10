package com.rpgtasks.character

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rpgtasks.FakeAuth
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.R
import com.rpgtasks.StorageSchema
import com.rpgtasks.databinding.ActivityCharacterBinding
import com.rpgtasks.tasks.TodoCpoint
import com.rpgtasks.tasks.TodoProfile

class CharacterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCharacterBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CHARACTER)
            .document(FakeAuth.currentUser.id)
            .get()
            .addOnSuccessListener { snap ->
                snap.toObject<Equipment>()?.also { updateUiCharacter(it) }
            }

    }

    private fun updateUiCharacter(equipment: Equipment) {
        binding.chaHead.load(equipment.imageEquip)
    }

}