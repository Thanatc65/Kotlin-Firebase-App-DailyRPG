package com.rpgtasks.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.OnProgressListener
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.MainActivity
import com.rpgtasks.databinding.ActivityRegisterBinding
import com.rpgtasks.tasks.Todo

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonConfirmRegister.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.registerUsername.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please write",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.editName.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please write",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.registerPassword.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please write",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email : String = binding.registerUsername.text.toString().trim{it <= ' '}
                    val password : String = binding.registerPassword.text.toString().trim{it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                               if (task.isSuccessful) {

                                   val firebaseUser : FirebaseUser = task.result!!.user!!

                                   Toast.makeText(
                                       this@RegisterActivity,
                                       "Success",
                                       Toast.LENGTH_SHORT
                                   ).show()

                                   val intent =
                                       Intent(this@RegisterActivity,LoginActivity::class.java)
                                   intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                   intent.putExtra("user_id",firebaseUser.uid)
                                   intent.putExtra("email_id",email)
                                   startActivity(intent)
                                   finish()

                               } else {
                                   Toast.makeText(
                                       this@RegisterActivity,
                                       task.exception!!.message.toString(),
                                       Toast.LENGTH_SHORT
                                   ).show()
                               }

                            }
                        )
                }
            }
        }

    }
}