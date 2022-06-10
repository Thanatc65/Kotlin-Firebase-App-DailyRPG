package com.rpgtasks

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rpgtasks.FirestoreSchema.TODOTASK
import com.rpgtasks.databinding.ActivityCreateTodoBinding
import com.rpgtasks.tasks.Todo
import java.util.*


class CreateTodoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreateTodoBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            buttonSetTime.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute: Int = calendar.get(Calendar.MINUTE)
                    val timePickerDialog = TimePickerDialog(this@CreateTodoActivity,
                        { timePicker, hourOfDay, minutes ->
                            val AMandPm: String
                            = if (hourOfDay >= 12) {
                                "PM"
                            } else {
                                "AM"
                            }
                            createTodoTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + AMandPm)
                            createTodoTime.setText("$hourOfDay:$minutes")
                        }, currentHour, currentMinute,false
                    )
                    timePickerDialog.show()
            }

            createButton.setOnClickListener {

                val todoCreate = Todo(
                    createTodoName.text.toString() ,
                    createTodoDescrip.text.toString(),
                    createTodoTime.text.toString()
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(TODOTASK)
                    .document()
                    .set(todoCreate, SetOptions.merge())
                    .addOnSuccessListener {
                        Toast.makeText(this@CreateTodoActivity, "Saved", Toast.LENGTH_SHORT).show()
                        Intent(this@CreateTodoActivity, MainActivity::class.java)
                            .also { startActivity(it) }
                    }

            }
        }
    }

}