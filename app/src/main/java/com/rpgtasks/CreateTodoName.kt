package com.rpgtasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rpgtasks.databinding.ActivityCreateTodoBinding
import com.rpgtasks.databinding.ActivityCreateTodoNameBinding
import com.rpgtasks.tasks.Todo
import com.rpgtasks.tasks.Todotask

class CreateTodoName : AppCompatActivity() {

    private val binding by lazy { ActivityCreateTodoNameBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            buttonCreateName.setOnClickListener {
                val todoName = Todotask(
                    createProfileName.text.toString()
                )

                db.collection("userId").document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.USERNAME)
                    .document(FakeAuth.currentUser.id)
                    .set(todoName, SetOptions.merge())
                    .addOnSuccessListener {
                        Toast.makeText(this@CreateTodoName, "Saved", Toast.LENGTH_SHORT).show()
                        Intent(this@CreateTodoName, MainActivity::class.java)
                            .also { startActivity(it) }
                    }
            }
        }
    }
}