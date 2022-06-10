package com.rpgtasks

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.rpgtasks.StorageSchema.PROFILE
import com.rpgtasks.databinding.ActivityCreateTodoProfileBinding
import com.rpgtasks.databinding.ActivityMainBinding
import com.rpgtasks.tasks.Todo
import com.rpgtasks.tasks.TodoProfile
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.util.*


class CreateTodoProfile : AppCompatActivity() {

    private var bannerUri: Uri? = null // remember picked URI

    companion object {
        const val REQUEST_IMAGE_GET = 1 // to identify which result is received
    }
    private val binding by lazy { ActivityCreateTodoProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            // Launch gallery to pick a banner
            binding.profileImagePick.setOnClickListener {
                Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "image/*"
                    resolveActivity(packageManager)
                        ?.also { startActivityForResult(this, REQUEST_IMAGE_GET) }
                        ?: Toast.makeText(
                            this@CreateTodoProfile ,
                            "No pickers available",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }

            // Save new restaurant
            binding.createProfileButton.setOnClickListener (this::save)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Ignore invalid results
        if (resultCode != Activity.RESULT_OK) return
        if (data?.data == null) return
        val uri = data.data

        // Handle result
        when (requestCode) {
            REQUEST_IMAGE_GET -> {
                this.bannerUri = uri
                binding.profileImagePick.load(uri)
            }
        }
    }

    private fun setCanSave(enabled: Boolean) {
        binding.createProfileButton.isEnabled = enabled
        binding.createProfileButton.text = if (enabled) "Save Profile" else "Saving"
    }

    private fun saveToStorage(uri: Uri?, path: String): Task<String>? {
        val ref = Firebase.storage.reference.child(path)

        return uri?.let {
            ref
                .putFile(uri)
                .continueWithTask {
                    if (!it.isSuccessful) throw it.exception ?: UnknownError()
                    ref.downloadUrl
                }
                .continueWith {
                    if (!it.isSuccessful) throw it.exception ?: UnknownError()
                    it.result.toString()
                }
        }
    }

    private fun save(_view: View) = lifecycleScope.launch { saveAsync() }

    private suspend fun saveAsync() {
        try {
            setCanSave(false)

            // Start saving pics

            val saveBannerPromise = saveToStorage(
                this.bannerUri,
                "${StorageSchema.PROFILE}/${StorageSchema.PROFILE_PIC}/${FakeAuth.currentUser.id}.jpg"
            )

            // Gather data (await storage saves)
            val profile = TodoProfile(
                saveBannerPromise?.await() ?: ""
            )

            // Save to database
            Firebase.firestore
                .collection(StorageSchema.PROFILE)
                .document(FakeAuth.currentUser.id)
                .set(profile, SetOptions.merge())
                .await()

            // Navigate to main
            Intent(this, MainActivity::class.java)
                .also { startActivity(it) }
        } catch (ex: Exception) {
            setCanSave(true)
            // handle exceptions here
        }
    }

}