package com.rpgtasks.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rpgtasks.MainActivity
import com.rpgtasks.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy{ActivityLoginBinding.inflate(layoutInflater)}
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            Intent(this@LoginActivity,RegisterActivity::class.java)
                .also { startActivity(it) }
        }

        binding.buttonLogin.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.editTextUsername.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please write",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.editTextPassword.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please write",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email : String = binding.editTextUsername.text.toString().trim{it <= ' '}
                    val password : String = binding.editTextPassword.text.toString().trim{it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful){

                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login Success",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val user = User(
                                    binding.editTextUsername.text.toString() ,
                                )

                                db.collection("userId")
                                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                                    .set(user, SetOptions.merge())

                                val intent =
                                    Intent(this@LoginActivity,MainActivity::class.java)

                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()

                            }else{

                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                        }

                }
            }
        }
        
    }
}