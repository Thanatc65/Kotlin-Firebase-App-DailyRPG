package com.rpgtasks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rpgtasks.databinding.ActivityMainBinding
import com.rpgtasks.databinding.LevelUpDialogBinding
import com.rpgtasks.tasks.TodoStatusPoint
import com.rpgtasks.tasks.*


class MainActivity : AppCompatActivity(){

    // Layout & VM
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm: TaskViewModel by viewModels()

    // Nav
    private val navController by lazy { findNavController(R.id.main_nav_host) }
    private val destinations =
        setOf(R.id.todayFragment, R.id.tomorrowFragment, R.id.foreverFragment,
            R.id.showFinishFragment,R.id.statusFragment)
    private val appBarConfiguration by lazy {
        AppBarConfiguration
            .Builder(destinations)
            .setOpenableLayout(binding.mainDrawerLayout)
            .build()
    }

    // Fields
    private var currentProgress = 0

    //level
    private var level = 1
    private var maxExp = 100
    private var cpoint = 0

    private val db = FirebaseFirestore.getInstance()

    // Lifecycles
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNav()

        updateUiLevel(TodoProgress())
        updateCpoint(TodoCpoint())

        vm.events.observe(this) { event ->

                deleteEvent()

        }
//        binding.profileImage.setOnClickListener {
//            Intent(this@MainActivity, CreateTodoProfile::class.java)
//                .also { startActivity(it) }
//        }
        binding.nameUserLabel.setOnClickListener {
            Intent(this@MainActivity, CreateTodoName::class.java)
                .also { startActivity(it) }
        }

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.USERNAME)
            .document(FakeAuth.currentUser.id)
            .get()
            .addOnSuccessListener { snap ->
                snap.toObject<Todotask>()?.also { updateUiAccount(it) }
            }

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(StorageSchema.PROFILE)
            .document(FakeAuth.currentUser.id)
            .get()
            .addOnSuccessListener { snap ->
                snap.toObject<TodoProfile>()?.also { updateUiProfile(it) }
            }

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.LEVELUSER)
            .document(FakeAuth.currentUser.id)
            .addSnapshotListener { snapshot, e ->
                snapshot?.toObject<TodoProgress>()?.also { updateLevel(it) }
            }

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CPOINTS)
            .document(FakeAuth.currentUser.id)
            .addSnapshotListener { snapshot, e ->
                snapshot?.toObject<TodoCpoint>()?.also { updateUICpoint(it) }
            }

    }

    private fun updateUICpoint(todo : TodoCpoint){

        db.collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CPOINTS)
            .document(FakeAuth.currentUser.id)
            .set(todo, SetOptions.merge())

        updateCpoint(todo)
    }

    private fun updateCpoint(todo : TodoCpoint){
        cpoint = todo.cpoint
    }

    private fun updateLevel(todoprogress : TodoProgress){
        db.collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.LEVELUSER)
            .document(FakeAuth.currentUser.id)
            .set(todoprogress, SetOptions.merge())

        updateUiLevel(todoprogress)

    }

    private fun updateUiLevel(progresslevel: TodoProgress){
        currentProgress = progresslevel.currentExp
        maxExp = progresslevel.currentMaxExp
        level = progresslevel.userLv

        binding.currentExp.text = currentProgress.toString()
        binding.maxExp.text = maxExp.toString()
        binding.progressBar.progress = currentProgress
        binding.userLevel.text = level.toString()

    }
    private fun updateUiAccount(username: Todotask) {
        binding.userInputName.text = username.name
    }
    private fun updateUiProfile(profile: TodoProfile) {
        binding.profileImage.load(profile.profilepicture)
    }

    private fun deleteEvent() {

        binding.progressBar.max = maxExp

                if (currentProgress <= maxExp) {
                    currentProgress += 20
                    binding.progressBar.progress = currentProgress
                    binding.currentExp.text = "$currentProgress"
                }

                if (currentProgress == maxExp){
                    currentProgress = 0
                    binding.progressBar.progress = currentProgress
                    binding.currentExp.text = "$currentProgress"

                    //normal dialog
//                    val lvup = callingActivity.let { AlertDialog.Builder(this) }
//                    lvup.setMessage(getString(R.string.congratulations))
//                    lvup.create().show()

                    level += 1
                    binding.userLevel.text = "$level"

                    maxExp += 20
                    binding.progressBar.max = maxExp
                    binding.maxExp.text = "$maxExp"

                    cpoint += 1

                    val lvupDialog = LayoutInflater.from(this).inflate(R.layout.level_up_dialog, null)
                    val builder = AlertDialog.Builder(this)
                        .setView(lvupDialog)
                    builder.create().show()

                }

        val todoprogress = TodoProgress(
            currentProgress,
            maxExp,
            level,
        )

        db.collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.LEVELUSER)
            .document(FakeAuth.currentUser.id)
            .set(todoprogress, SetOptions.merge())

        val todo = TodoCpoint(
            cpoint
        )
        db.collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CPOINTS)
            .document(FakeAuth.currentUser.id)
            .set(todo, SetOptions.merge())


    }


    private fun setupNav() {
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.showFinishFragment -> setNavigationVisible(false)
                R.id.statusFragment -> setNavigationVisible(false)
                else -> setNavigationVisible(true)
            }
        }
    }

    private fun setNavigationVisible(visible: Boolean) {
        binding.mainBottomNavigationView.visibility = if (visible) View.VISIBLE else View.GONE
        binding.mainNavigationView.visibility = if (visible) View.VISIBLE else View.GONE
        binding.mainDrawerLayout.setDrawerLockMode(if (visible) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_UNLOCKED)

        if (visible) {
            binding.mainNavigationView.setupWithNavController(navController)
            binding.mainBottomNavigationView.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() = with(binding.mainDrawerLayout) {
        when {
            isDrawerOpen(GravityCompat.START) -> closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }
}
